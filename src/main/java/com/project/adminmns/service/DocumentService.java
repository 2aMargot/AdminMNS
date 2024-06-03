package com.project.adminmns.service;

import com.project.adminmns.dao.DocumentDao;
import com.project.adminmns.model.Document;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class DocumentService {

    DocumentDao documentDao;

    public List<Document> documentList() {
        return documentDao.findAll();
    }

    public ResponseEntity<Document> getDocument(@PathVariable int id) {

        Optional<Document> documentOptional = this.documentDao.findById(id);

        if (documentOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(documentOptional.get(), HttpStatus.OK);
    }

    public ResponseEntity<Document> addDocument(@Valid @RequestBody Document newDocument) {
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

    public ResponseEntity<Document> updateDocument(@Valid @RequestBody Document document, @PathVariable int id) {
        document.setId(id);

        Optional<Document> documentOptional = documentDao.findById(document.getId());

        //l'utilisateur tente de modifier un document qui n'existe pas/plus
        if (documentOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.documentDao.save(document);
        return new ResponseEntity<>(documentOptional.get(), HttpStatus.OK);
    }

    public ResponseEntity<Document> deleteDocument(@PathVariable int id) {

        Optional<Document> documentOptional = this.documentDao.findById(id);

        if (documentOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.documentDao.deleteById(id);
        return new ResponseEntity<>(documentOptional.get(), HttpStatus.OK);
    }

}
