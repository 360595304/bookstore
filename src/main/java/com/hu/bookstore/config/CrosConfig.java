package com.hu.bookstore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域解决方案
 * @author NieChangan
 */
@Configuration
public class CrosConfig implements WebMvcConfigurer {

    /**
     * Configure cross origin requests processing.
     *
     * @since 4.2
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // 设置允许跨域请求的域名
                .allowedOriginPatterns("*")
                // 是否允许证书（cookies）
                .allowCredentials(true)
                // 设置允许的方法
                .allowedMethods("GET","POST","PUT","DELETE","OPTIONS","HEAD")
                // 跨域允许时间
                .maxAge(3600)
                .allowedHeaders("*");
    }
}
