package com.bocft.bocpet.webapi.module.sysmgt.util;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.util.StrUtil;
import com.bocft.bocpet.webapi.common.constant.SysConfigContextHolder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class FileUtil {
    public static boolean isUploadFileTypeSupported(MultipartFile file) {
        String supportedUploadFileType = SysConfigContextHolder.getSysConfig("SUPPORTED_UPLOAD_FILE_TYPE", String.class);
        Set<String> fileTypes = new HashSet<>(StrUtil.split(supportedUploadFileType, ','));
        String uploadFileType;
        try {
            uploadFileType = FileTypeUtil.getType(file.getInputStream());
        } catch (IOException e) {
            return false;
        }
        if (uploadFileType == null) {
            uploadFileType = StrUtil.subAfter(file.getOriginalFilename(), '.', true);
        }
        return fileTypes.contains(uploadFileType);
    }
}
