package com.bocft.bocpet.webapi.module.sysmgt.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.log.Log;
import com.bocft.bocpet.webapi.common.exception.BocpetBizException;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 文件下载工具类
 *
 * @date 2020/8/5 21:45
 */
public class DownloadUtil {

    private static final Log log = Log.get();

    public static void download(String fileName, byte[] fileBytes, HttpServletResponse response) {
        try {
            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename=\"" + URLUtil.encode(fileName) + "\"");
            response.addHeader("Content-Length", "" + fileBytes.length);
            response.setContentType("application/octet-stream;charset=UTF-8");
            IoUtil.write(response.getOutputStream(), true, fileBytes);
        } catch (IOException e) {
            log.error(">>> 下载文件异常，具体信息为：{}", e.getMessage());
            throw new BocpetBizException("下载文件异常");
        }
    }

    /**
     * 下载文件
     *
     * @param file     要下载的文件
     * @param response 相应
     */
    public static void download(File file, HttpServletResponse response) {
        // 获取文件字节
        byte[] fileBytes = FileUtil.readBytes(file);
        //获取文件名称
        String fileName;
        try {
            fileName = URLEncoder.encode(file.getName(), CharsetUtil.UTF_8);
        } catch (UnsupportedEncodingException e) {
            log.error(">>> 下载文件异常，，具体信息为：{}", e.getMessage());
            throw new BocpetBizException("下载文件异常");
        }
        //下载文件
        download(fileName, fileBytes, response);
    }
}
