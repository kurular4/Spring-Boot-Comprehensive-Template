package com.example.demo.dto;

import com.example.demo.model.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Accessors(chain = true)
public class UserDTO {

    @Getter
    @Setter
    @NotNull
    private String username;

    @Getter
    @Setter
    @NotNull
    private String password;

    @Getter
    @Setter
    @Email
    @NotNull
    private String email;

    @Getter
    @Setter
    private List<Role> roles;
}
