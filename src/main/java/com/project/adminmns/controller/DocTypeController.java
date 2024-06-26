package com.project.adminmns.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.adminmns.dao.DocTypeDao;
import com.project.adminmns.model.DocType;
import com.project.adminmns.security.AdminPermission;
import com.project.adminmns.view.DocTypeView;
import com.project.adminmns.view.TrainingView;
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
public class DocTypeController {

    protected DocTypeDao docTypeDao;

    @GetMapping("/doctype/list")
    @JsonView(DocTypeView.class)
    @AdminPermission
    public List<DocType> liste() {
        // affiche la liste de tous les doctypes
        return docTypeDao.findAll();
    }


    @GetMapping("/doctype/{id}")
    @JsonView(DocTypeView.class)
    @AdminPermission
    public ResponseEntity<DocType> get(@PathVariable int id) {
        // affiche un doctype par son ID
        Optional<DocType> docTypeOptional = this.docTypeDao.findById(id);

        if (docTypeOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(docTypeOptional.get(), HttpStatus.OK);
    }

    @PostMapping("/doctype")
    @JsonView(DocTypeView.class)
    @AdminPermission
    public ResponseEntity<DocType> add(@Valid @RequestBody DocType newDocType) {

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

    @PutMapping("/doctype/{id}")
    @JsonView(DocTypeView.class)
    @AdminPermission
    public ResponseEntity<DocType> update(@Valid @RequestBody DocType docType, @PathVariable int id) {
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

    @DeleteMapping("/doctype/{id}")
    @JsonView(DocTypeView.class)
    @AdminPermission
    public ResponseEntity<DocType> delete(@PathVariable int id) {
        // suppression d'un doctype
        Optional<DocType> docTypeOptional = this.docTypeDao.findById(id);

        if (docTypeOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        docTypeDao.deleteById(id);
        return new ResponseEntity<>(docTypeOptional.get(), HttpStatus.OK);
    }
}