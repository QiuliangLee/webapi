package com.bocft.bocpet.webapi.module.sysmgt.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bocft.bocpet.webapi.common.constant.SysConfigContextHolder;
import com.bocft.bocpet.webapi.common.exception.BocpetBizException;
import com.bocft.bocpet.webapi.common.util.UserUtils;
import com.bocft.bocpet.webapi.module.sysmgt.entity.SysFile;
import com.bocft.bocpet.webapi.module.sysmgt.mapper.SysFileMapper;
import com.bocft.bocpet.webapi.module.sysmgt.service.ISysFileService;
import com.bocft.bocpet.webapi.module.sysmgt.util.DownloadUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 文件信息表 服务实现类
 * </p>
 *
 * @author liuzhe
 * @since 2020-10-14
 */
@Service
public class SysFileServiceImpl extends ServiceImpl<SysFileMapper, SysFile> implements ISysFileService {

    @Override
    public Long saveFile(MultipartFile file) {
        String defaultBucket = SysConfigContextHolder.getSysConfig("DEFAULT_FILE_BUCKET", String.class);
        return this.saveFile(file, defaultBucket);
    }

    @Override
    public Long saveFile(byte[] fileBytes, String fileName) {
        String defaultBucket = SysConfigContextHolder.getSysConfig("DEFAULT_FILE_BUCKET", String.class);
        return this.saveFile(fileBytes, fileName, defaultBucket);
    }

    @Override
    public Long saveFile(MultipartFile file, String bucket) {
        try {
            byte[] bytes = file.getBytes();
            return this.saveFile(bytes, file.getOriginalFilename(), bucket);
        } catch (IOException e) {
            throw new BocpetBizException("存储文件失败");
        }
    }

    @Override
    public Long saveFile(byte[] fileBytes, String fileName, String bucket) {
        String defaultBucket = SysConfigContextHolder.getSysConfig("DEFAULT_FILE_BUCKET", String.class);
        if (StrUtil.isBlank(bucket)) {
            bucket = defaultBucket;
        }
        // 生成文件的唯一id
        Long fileId = IdWorker.getId();
        // 获取文件原始名称
        String originalFilename = fileName;

        // 获取文件后缀
        String fileSuffix = "";

        if (ObjectUtil.isNotEmpty(originalFilename)) {
            fileSuffix = StrUtil.subAfter(originalFilename, ".", true);
        }
        String finalName = fileId.toString();
        // 生成文件的最终名称
        if (StrUtil.isNotEmpty(fileSuffix)) {
            finalName = finalName + "." + fileSuffix;
        }
        // 按月份存储
        String month = DateUtil.format(LocalDateTime.now(), "yyyyMM");
        String absPath = SysConfigContextHolder.getSysConfig("FILE_UPLOAD_PATH", String.class)
                + File.separator + bucket + File.separator + month + File.separator + finalName;
        // 存储文件
        FileUtil.writeBytes(fileBytes, absPath);

        // 计算文件大小kb
        long fileSizeKb = Convert.toLong(NumberUtil.div(new BigDecimal(fileBytes.length), BigDecimal.valueOf(1024))
                .setScale(0, BigDecimal.ROUND_HALF_UP));

        //计算文件大小信息
        String fileSizeInfo = FileUtil.readableFileSize(fileBytes.length);

        // 存储文件信息
        SysFile sysFile = new SysFile();
        sysFile.setId(fileId);
        sysFile.setBucket(bucket);
        sysFile.setFileObjectName(finalName);
        sysFile.setFileOriginName(originalFilename);
        sysFile.setFileSuffix(fileSuffix);
        sysFile.setFileSizeKb(fileSizeKb);
        sysFile.setFileSizeInfo(fileSizeInfo);
        sysFile.setFilePath(absPath);
        sysFile.setCreateUser(UserUtils.getCurrentUserName());
        sysFile.setCreateTime(LocalDateTime.now());
        this.save(sysFile);
        // 返回文件id
        return sysFile.getId();
    }

    @Override
    public void download(Long id, HttpServletResponse response) {
        SysFile sysFile = this.getById(id);
        if (sysFile == null) {
            throw new BocpetBizException(StrUtil.format("文件不存在, 文件id: {}", id));
        }
        byte[] fileBytes;
        try {
            if (!FileUtil.exist(sysFile.getFilePath())) {
                throw new BocpetBizException(StrUtil.format("文件不存在, 文件路径: {}", sysFile.getFilePath()));
            } else {
                fileBytes = FileUtil.readBytes(sysFile.getFilePath());
            }
        } catch (Exception e) {
            log.error(">>> 获取文件流异常，具体信息为：{}", e);
            throw new BocpetBizException("获取文件流异常", e);
        }
        DownloadUtil.download(sysFile.getFileOriginName(), fileBytes, response);
    }

    @Override
    public void moveFile(Long id, String destBucket) {
        SysFile sysFile = this.getById(id);
        if (sysFile == null) {
            throw new BocpetBizException("文件不存在, id: " + id);
        }
        File oriFile = new File(sysFile.getFilePath());
        if (!oriFile.exists()) {
            throw new BocpetBizException("文件不存在, path: " + sysFile.getFilePath());
        }
        if (StrUtil.equals(destBucket, sysFile.getBucket())) {
            return;
        }
        String newPath = SysConfigContextHolder.getSysConfig("FILE_UPLOAD_PATH", String.class) +
                File.separator + destBucket + File.separator + sysFile.getFileObjectName();
        File newFile = new File(newPath);
        FileUtil.move(oriFile, newFile, true);
        this.lambdaUpdate().set(SysFile::getFilePath, newPath)
                .set(SysFile::getBucket, destBucket)
                .eq(SysFile::getId, id).update();
    }

    @Override
    public boolean delete(Long id) {
        SysFile sysFile = this.getById(id);
        if (sysFile == null) {
            throw new BocpetBizException("不存在的文件id: " + id);
        }
        File file = new File(sysFile.getFilePath());
        return file.exists() && file.delete() && this.removeById(id);
    }

    @Override
    public File getFileById(Long id) {
        SysFile sysFile = this.getById(id);
        if (sysFile == null) {
            throw new BocpetBizException(StrUtil.format("文件不存在, 文件id: {}", id));
        }
        File res = new File(sysFile.getFilePath());
        if (!res.exists()) {
            throw new BocpetBizException("文件不存在, path: " + sysFile.getFilePath());
        }
        return res;
    }
}
