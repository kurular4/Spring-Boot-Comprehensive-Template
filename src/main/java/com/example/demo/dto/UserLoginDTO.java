package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

public class UserLoginDTO {

    @Getter
    @Setter
    @NotNull
    private String username;

    @Getter
    @Setter
    @NotNull
    private String password;

}
