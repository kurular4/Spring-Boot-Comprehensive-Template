package com.example.demo.controller;

import com.example.demo.dto.GenericResponse;
import com.example.demo.model.Document;
import com.example.demo.model.User;
import com.example.demo.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("document")
public class DocumentController {
    private final DocumentService documentService;

    @PutMapping(value = "create", consumes = "application/json")
    public GenericResponse create(@AuthenticationPrincipal User user, @Valid @RequestBody Document document) {
        user.getDocumentList().add(document);
        return documentService.create(document);
    }

    @PutMapping(value = "update/{id}", consumes = "application/json")
    public GenericResponse update(@PathVariable String id, @Valid @RequestBody Document document) {
        return documentService.update(id, document);
    }

    @DeleteMapping(value = "remove/{id}", consumes = "application/json")
    public GenericResponse update(@PathVariable String id) {
        return documentService.remove(id);
    }
}
