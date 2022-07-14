package com.bocft.bocpet.webapi.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        //mybatis-plus分页默认每页最大为500条记录,设置为-1表示取消500限制
        paginationInterceptor.setLimit(-1);
        return paginationInterceptor;
    }
}
