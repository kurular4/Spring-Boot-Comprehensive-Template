package com.example.demo.jwt;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    private final ModelMapper modelMapper;
    private final UserService userService;

    private final Set<String> skipUrls = new HashSet<>(Collections.singletonList("/user/create"));
    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return skipUrls.stream().anyMatch(p -> pathMatcher.match(p, request.getRequestURI()));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = resolveToken(httpServletRequest);

        try {
            if (token != null && validateToken(token)) {
                Authentication auth = getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (ExpiredJwtException exception) {
            exception.printStackTrace();
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    Authentication getAuthentication(String token) {
        User user = userService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());
    }

    String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        } else return null;
    }

    boolean validateToken(String token) throws JwtException {
        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        return !claims.getBody().getExpiration().before(new Date());
    }
}
