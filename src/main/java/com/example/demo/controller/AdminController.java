package com.example.demo.controller;

import com.example.demo.dto.GenericResponse;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("admin")
public class AdminController {
    private final AdminService adminService;

    @GetMapping(value = "all-users", produces = "application/json")
    public GenericResponse getUsers() {
        return adminService.allUsers();
    }

    @PutMapping(value = "set-roles", consumes = "application/json", produces = "application/json")
    public GenericResponse setRoles(@Valid @RequestBody UserDTO userDTO) {
        return adminService.setRoles(userDTO);
    }
}
