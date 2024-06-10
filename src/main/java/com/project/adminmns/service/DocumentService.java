package com.project.adminmns.service;

import com.project.adminmns.dao.DocumentDao;
import com.project.adminmns.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {

    private final DocumentDao documentDao;

    @Autowired
    public DocumentService(DocumentDao documentDao){
        this.documentDao = documentDao;
    }

    public List<Document> documentList() {
        return documentDao.findAll();
    }

    public ResponseEntity<Document> getDocument(int id) {

        Optional<Document> documentOptional = this.documentDao.findById(id);

        if (documentOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(documentOptional.get(), HttpStatus.OK);
    }

    public ResponseEntity<Document> addDocument(Document newDocument) {

        if (newDocument.getId() != null) {
            Optional<Document> documentOptional = this.documentDao.findById(newDocument.getId());

            if (documentOptional.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            this.documentDao.save(newDocument);

            return new ResponseEntity<>(documentOptional.get(), HttpStatus.OK);
        }

        documentDao.save(newDocument);

        return new ResponseEntity<>(newDocument, HttpStatus.CREATED);
    }

    public ResponseEntity<Document> updateDocument(Document document, int id) {

        Optional<Document> documentOptional = documentDao.findById(document.getId());

        if (documentOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        document.setId(id);
        this.documentDao.save(document);
        return new ResponseEntity<>(documentOptional.get(), HttpStatus.OK);
    }

    public ResponseEntity<Document> deleteDocument(int id) {

        Optional<Document> documentOptional = this.documentDao.findById(id);

        if (documentOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.documentDao.deleteById(id);
        return new ResponseEntity<>(documentOptional.get(), HttpStatus.OK);
    }

}
