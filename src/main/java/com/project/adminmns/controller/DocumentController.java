package com.project.adminmns.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.adminmns.dao.DocumentDao;
import com.project.adminmns.model.Document;
import com.project.adminmns.security.AdminPermission;
import com.project.adminmns.security.StudentPermission;
import com.project.adminmns.service.DocumentService;
import com.project.adminmns.view.DocumentView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@AllArgsConstructor
public class DocumentController {

    @Autowired
    DocumentService documentService;

    @GetMapping("/document/list")
    @JsonView(DocumentView.class)
    @AdminPermission
    public List<Document> list() {
        return documentService.documentList();
    }

    @GetMapping("/document/{id}")
    @JsonView(DocumentView.class)
    @AdminPermission
    public ResponseEntity<Document> get(@PathVariable int id) {
        return documentService.getDocument(id);
    }

    @PostMapping("/document")
    @JsonView(DocumentView.class)
    @AdminPermission
    public ResponseEntity<Document> add(@Valid @RequestBody Document newDocument) {
        return documentService.addDocument(newDocument);
    }

    @PutMapping("/document/{id}")
    @JsonView(DocumentView.class)
    @AdminPermission
    public ResponseEntity<Document> update(@Valid @RequestBody Document document, @PathVariable int id) {
        return documentService.updateDocument(document, id);
    }

    @DeleteMapping("/document/{id}")
    @JsonView(DocumentView.class)
    @AdminPermission
    public ResponseEntity<Document> delete(@PathVariable int id) {
        return documentService.deleteDocument(id);
    }
}