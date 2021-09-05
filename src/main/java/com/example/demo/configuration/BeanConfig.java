package com.example.demo.configuration;

import com.example.demo.jwt.JwtAuthenticationFilter;
import com.example.demo.jwt.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class BeanConfig {
    private final JwtAuthorizationFilter jwtAuthorizationFilter;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public FilterRegistrationBean<JwtAuthenticationFilter> jwtAuthenticationFilterFilterRegistrationBean() {
        FilterRegistrationBean<JwtAuthenticationFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(jwtAuthenticationFilter);
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<JwtAuthorizationFilter> jwtAuthorizationFilterFilterRegistrationBean() {
        FilterRegistrationBean<JwtAuthorizationFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(jwtAuthorizationFilter);
        return filterRegistrationBean;
    }
}
