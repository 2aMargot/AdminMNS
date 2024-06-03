package com.project.adminmns.service;

import com.project.adminmns.dao.DocTypeDao;
import com.project.adminmns.model.DocType;
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
public class DocTypeService {

    DocTypeDao docTypeDao;

    public List<DocType> doctypeList() {
        return docTypeDao.findAll();
    }

    public ResponseEntity<DocType> getDocType(@PathVariable int id) {
        // affiche un doctype par son ID
        Optional<DocType> docTypeOptional = this.docTypeDao.findById(id);

        if (docTypeOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(docTypeOptional.get(), HttpStatus.OK);
    }

    public ResponseEntity<DocType> addDocType(@Valid @RequestBody DocType newDocType) {
        //recherche d'un doctype sélectionné
        if (newDocType.getId() != null) {
            Optional<DocType> docTypeOptional = this.docTypeDao.findById(newDocType.getId());

            // l'utilisateur tente de modifier un doctype qui n'existe pas ou plus
            if (docTypeOptional.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            this.docTypeDao.save(newDocType);
            return new ResponseEntity<>(docTypeOptional.get(), HttpStatus.OK);
        }
        // ajout sauvé en base de données
        docTypeDao.save(newDocType);
        return new ResponseEntity<>(newDocType, HttpStatus.CREATED);
    }

    public ResponseEntity<DocType> updateDocType(@Valid @RequestBody DocType docType, @PathVariable int id) {
        // modification sur un doctype
        docType.setId(id);

        Optional<DocType> docTypeOptional = docTypeDao.findById(docType.getId());

        //l'utilisateur tente de modifier un docType qui n'existe pas/plus
        if (docTypeOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.docTypeDao.save(docType);
        return new ResponseEntity<>(docTypeOptional.get(), HttpStatus.OK);
    }

    public ResponseEntity<DocType> deleteDocType(@PathVariable int id) {
        // suppression d'un doctype
        Optional<DocType> docTypeOptional = this.docTypeDao.findById(id);

        if (docTypeOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        docTypeDao.deleteById(id);
        return new ResponseEntity<>(docTypeOptional.get(), HttpStatus.OK);
    }
}
