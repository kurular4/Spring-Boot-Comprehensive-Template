package com.example.demo.configuration;

import com.example.demo.jwt.JwtAuthorizationFilter;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Override
    public void configure(HttpSecurity http) {
        JwtAuthorizationFilter customFilter = new JwtAuthorizationFilter();
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
