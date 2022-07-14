package com.bocft.bocpet.webapi.module.sysmgt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bocft.bocpet.webapi.module.sysmgt.entity.SysFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * <p>
 * 文件信息表 服务类
 * </p>
 *
 * @author liuzhe
 * @since 2020-10-14
 */
public interface ISysFileService extends IService<SysFile> {

    Long saveFile(MultipartFile file);

    Long saveFile(byte[] fileBytes, String fileName);

    Long saveFile(MultipartFile file, String bucket);

    Long saveFile(byte[] fileBytes, String fileName, String bucket);

    void download(Long id, HttpServletResponse response);

    void moveFile(Long id, String destBucket);

    boolean delete(Long id);

    File getFileById(Long id);

}
