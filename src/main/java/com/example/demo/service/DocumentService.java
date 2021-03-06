package com.example.demo.service;

import com.example.demo.dto.GenericResponse;
import com.example.demo.exception.document.DocumentNotFoundException;
import com.example.demo.model.Document;
import com.example.demo.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DocumentService {

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    GenericResponseService genericResponseService;

    public GenericResponse create(Document document) {
        Objects.requireNonNull(document);
        if (documentRepository.save(document) != null) {
            return genericResponseService.createResponseNoError(document);
        } else throw new RuntimeException();
    }

    public GenericResponse update(String id, Document documentUpdated) {
        Document document = documentRepository.findById(id).orElseThrow(DocumentNotFoundException::new);

        document.setAuthor(documentUpdated.getAuthor());
        document.setName(documentUpdated.getName());
        document.setPageCount(documentUpdated.getPageCount());

        if (documentRepository.save(document) != null) {
            return genericResponseService.createResponseNoError(document);
        } else throw new RuntimeException();
    }

    public GenericResponse remove(String id) {
        if (!documentRepository.findById(id).isPresent()) {
            throw new DocumentNotFoundException();
        }

        documentRepository.deleteById(id);
        return genericResponseService.createResponseNoError(true);
    }
}
