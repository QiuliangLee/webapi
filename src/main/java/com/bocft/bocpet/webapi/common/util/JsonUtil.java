package com.bocft.bocpet.webapi.common.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Json读取工具类
 */
public class JsonUtil {
    /**
     * 读取json文件
     *
     * @param inputStream json文件流
     * @return 返回json字符串
     */
    public static String readJsonFile(InputStream inputStream) {
        String jsonStr = "";
        try {
            Reader reader = new InputStreamReader(inputStream, "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
