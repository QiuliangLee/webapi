package com.bocft.bocpet.webapi.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Jackson配置
 *
 * @author William
 */
@Configuration
public class JacksonConfig {
    @Value("${spring.jackson.date-format}")
    private String dateFormat;


    /**
     * Jackson全局转化long类型为String，解决jackson序列化时long类型缺失精度问题
     * <p>
     *
     * @return Jackson2ObjectMapperBuilderCustomizer 注入的对象
     */
    @Bean
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {

        return jacksonObjectMapperBuilder -> {
            jacksonObjectMapperBuilder.serializerByType(Long.TYPE, ToStringSerializer.instance);
            jacksonObjectMapperBuilder.serializerByType(Long.class, ToStringSerializer.instance);
            jacksonObjectMapperBuilder.serializerByType(long.class, ToStringSerializer.instance);
            jacksonObjectMapperBuilder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer((DateTimeFormatter.ofPattern(dateFormat))));
            jacksonObjectMapperBuilder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(dateFormat)));
//            jacksonObjectMapperBuilder.deserializerByType(String.class, new JsonHtmlXssDeserializer(String.class));
        };
    }


    /**
     * 对出参的json进行转义
     */
    class JsonHtmlXssSerializer extends JsonSerializer<String> {

        public JsonHtmlXssSerializer(Class<String> string) {
            super();
        }

        @Override
        public Class<String> handledType() {
            return String.class;
        }

        @Override
        public void serialize(String value, JsonGenerator jsonGenerator,
                              SerializerProvider serializerProvider) throws IOException {
            if (value != null) {
                String encodedValue = HtmlUtils.htmlEscape(value);
                jsonGenerator.writeString(encodedValue);
            }
        }
    }

    /**
     * 对入参的json进行转义
     */
    class JsonHtmlXssDeserializer extends JsonDeserializer<String> {

        public JsonHtmlXssDeserializer(Class<String> string) {
            super();
        }

        @Override
        public Class<String> handledType() {
            return String.class;
        }

        @Override
        public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            String value = jsonParser.getValueAsString();
            if (value != null) {
                return HtmlUtils.htmlEscape(value);
            }
            return value;
        }
    }
}