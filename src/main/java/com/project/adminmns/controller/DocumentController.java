package com.project.adminmns.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.adminmns.dao.DocumentDao;
import com.project.adminmns.model.Document;
import com.project.adminmns.security.AdminPermission;
import com.project.adminmns.security.StudentPermission;
import com.project.adminmns.view.DocumentView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@AllArgsConstructor
public class DocumentController {

    protected DocumentDao documentDao;

    @GetMapping("/document/list")
    @JsonView(DocumentView.class)
    @AdminPermission
    public List<Document> liste() {
        return documentDao.findAll();
    }


    @GetMapping("/document/{id}")
    @JsonView(DocumentView.class)
    @AdminPermission
    public ResponseEntity<Document> get(@PathVariable int id) {

        Optional<Document> documentOptional = this.documentDao.findById(id);

        if (documentOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(documentOptional.get(), HttpStatus.OK);
    }

    @PostMapping("/document")
    @JsonView(DocumentView.class)
    @AdminPermission
    public ResponseEntity<Document> add(@Valid @RequestBody Document newDocument) {
        if (newDocument.getId() != null) {
            Optional<Document> documentOptional = this.documentDao.findById(newDocument.getId());

            // l'utilisateur tente de modifier un document qui n'existe pas ou plus
            if (documentOptional.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            this.documentDao.save(newDocument);

            return new ResponseEntity<>(documentOptional.get(), HttpStatus.OK);
        }

        documentDao.save(newDocument);

        return new ResponseEntity<>(newDocument, HttpStatus.CREATED);
    }

    @PutMapping("/document/{id}")
    @JsonView(DocumentView.class)
    @AdminPermission
    public ResponseEntity<Document> update(@Valid @RequestBody Document document, @PathVariable int id) {
        document.setId(id);

        Optional<Document> documentOptional = documentDao.findById(document.getId());

        //l'utilisateur tente de modifier un document qui n'existe pas/plus
        if (documentOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.documentDao.save(document);
        return new ResponseEntity<>(documentOptional.get(), HttpStatus.OK);
    }

    @DeleteMapping("/document/{id}")
    @JsonView(DocumentView.class)
    @AdminPermission
    public ResponseEntity<Document> delete(@PathVariable int id) {

        Optional<Document> documentOptional = this.documentDao.findById(id);

        if (documentOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        documentDao.deleteById(id);
        return new ResponseEntity<>(documentOptional.get(), HttpStatus.OK);
    }
}