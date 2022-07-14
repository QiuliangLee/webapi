package com.bocft.bocpet.webapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class WebApiBoot {
    public static void main(String[] args) {
        SpringApplication.run(WebApiBoot.class);
    }

}
