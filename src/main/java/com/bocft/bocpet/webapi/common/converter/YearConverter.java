package com.bocft.bocpet.webapi.common.converter;

import cn.hutool.core.convert.Converter;
import cn.hutool.core.convert.ConverterRegistry;

import java.time.Year;

public class YearConverter implements Converter<Year> {
    ConverterRegistry converterRegistry = ConverterRegistry.getInstance();

    @Override
    public Year convert(Object value, Year defaultValue) throws IllegalArgumentException {
        return Year.parse(converterRegistry.convert(CharSequence.class, value));
    }
}
