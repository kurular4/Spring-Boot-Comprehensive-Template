package com.example.demo.controller;

import com.example.demo.dto.GenericResponse;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.Document;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    @PutMapping(value = "create", consumes = "application/json")
    public GenericResponse create(@Valid @RequestBody UserDTO userDTO) {
        return userService.create(userDTO);
    }

    @PutMapping(value = "update", consumes = "application/json")
    public GenericResponse update(@PathVariable String id, @Valid @RequestBody UserDTO userDTO) {
        return userService.update(id, userDTO);
    }

    @DeleteMapping(value = "remove/{id}")
    public GenericResponse remove(@PathVariable String id) {
        return userService.remove(id);
    }

    @GetMapping(value = "documents")
    public GenericResponse getDocuments(@AuthenticationPrincipal User user) {
        return userService.findAllDocuments(user);
    }

    @PutMapping(value = "add-document", consumes = "application/json")
    public GenericResponse addDocument(@AuthenticationPrincipal User user, @RequestBody Document document) {
        return userService.addDocument(user, document);
    }
}
