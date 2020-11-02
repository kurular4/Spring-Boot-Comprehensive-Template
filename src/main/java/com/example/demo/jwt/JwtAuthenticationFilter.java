package com.example.demo.jwt;

import com.example.demo.constant.ErrorMessageConstant;
import com.example.demo.dto.UserLoginDTO;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.GenericResponseService;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMilliseconds;

    private static final String loginPath = "/signin";

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserService userService;

    @Autowired
    GenericResponseService genericResponseService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(loginPath));
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @PostConstruct
    protected void initSecretKey() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String username, List<Role> roles) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", roles);
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserLoginDTO userLoginDTO = new ObjectMapper().readValue(request.getInputStream(), UserLoginDTO.class);
            User user = userService.loadUserByUsername(userLoginDTO.getUsername());

            if (user != null && bCryptPasswordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
                return authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(userLoginDTO.getUsername(), userLoginDTO.getPassword(), new ArrayList<>()));
            } else throw new BadCredentialsException(ErrorMessageConstant.BAD_CREDENTIALS);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        response.addHeader("token", createToken(user.getUsername(), user.getRoles()));
        new ObjectMapper().writeValue(response.getOutputStream(), genericResponseService.createResponseNoError(user));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        throw new BadCredentialsException(ErrorMessageConstant.BAD_CREDENTIALS);
    }
}
