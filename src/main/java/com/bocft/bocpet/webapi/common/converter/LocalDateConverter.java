package com.bocft.bocpet.webapi.common.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 日期转换类
 * 将标准日期、标准日期时间转换成Date类型
 */
public class LocalDateConverter implements Converter<String, LocalDate> {

    private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";
    private static final String shortDateFormat = "yyyy-MM-dd";


    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormat);
    private static final DateTimeFormatter sdtf = DateTimeFormatter.ofPattern(shortDateFormat);


    @Override
    public LocalDate convert(String value) {
        if (value == null || value.trim().equals("") || value.equalsIgnoreCase("null")) {
            return null;
        }

        value = value.trim();

        try {
            DateTimeFormatter formatter;
            if (value.contains(":")) {
                formatter = dtf;
            } else {
                formatter = sdtf;
            }
            return LocalDate.parse(value, formatter);
        } catch (Exception e) {
            throw new RuntimeException(String.format("parser %s to Date fail", value));
        }
    }
}
