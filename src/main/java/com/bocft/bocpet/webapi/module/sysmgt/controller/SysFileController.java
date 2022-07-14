package com.bocft.bocpet.webapi.module.sysmgt.controller;


import cn.hutool.core.util.StrUtil;
import com.bocft.bocpet.webapi.common.pojo.Result;
import com.bocft.bocpet.webapi.module.sysmgt.service.ISysFileService;
import com.bocft.bocpet.webapi.module.sysmgt.util.FileUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 文件信息表 前端控制器
 * </p>
 *
 * @author liuzhe
 * @since 2020-10-14
 */
@RestController
@RequestMapping("/file")
public class SysFileController {
    @Resource
    ISysFileService sysFileService;

    @PostMapping("/upload")
    public Result upload(@RequestPart("file") MultipartFile file, String bucket) {
        if (!FileUtil.isUploadFileTypeSupported(file)) {
            return Result.err(-1, StrUtil.format("不支持的文件类型"));
        }
        Long fileId = sysFileService.saveFile(file, bucket);
        return Result.suc().putData("fileId", fileId)
                .putData("fileName", file.getOriginalFilename());
    }

    @GetMapping("/download")
    public void download(Long id, HttpServletResponse response) {
        sysFileService.download(id, response);
    }

    @DeleteMapping("/delete")
    public Result delete(Long id) {
        sysFileService.delete(id);
        return Result.suc();
    }
}
