package com.seatbooking.springboot.config;

import com.seatbooking.springboot.config.interceptor.JWTInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        System.out.println("registry:::%s2222");
        return new WebMvcConfigurer() {
            // 重写父类提供的跨域请求处理的接口
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // 添加映射路径
                registry.addMapping("/**")
                        .allowedOriginPatterns("*")                         // 放行哪些域名，可以多个
                        .allowCredentials(true)                             // 是否发送Cookie信息
                        .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS", "PATCH") // 放行哪些请求方式
                        .allowedHeaders("*")                                // 放行哪些原始域(头部信息)
                        .exposedHeaders("Header1", "Header2")               // 暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
                        .maxAge(3600);                                      // 预请求的结果有效期，默认1800分钟,3600是一小时
            }
        };
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        System.out.println("registry:::%s");
//        registry.addInterceptor(jwtInterceptor())
//                .addPathPatterns("/**")  // 拦截所有请求，通过判断token是否合法决定是否需要登录
//                .excludePathPatterns("/user/login","/pic/getImage","/pic/upload");
//    }
//
    @Bean
    public JWTInterceptor jwtInterceptor(){
        return new JWTInterceptor();
    }

}
