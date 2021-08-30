package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


// todo convert fields injections to constructor inj.
// todo return ResponseEntity instead of custom response model
// todo convert constant classes to enum
// todo use own jwt util lib
// todo refine authentication/authorization structure

@SpringBootApplication
@EnableJpaRepositories
@ServletComponentScan
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
