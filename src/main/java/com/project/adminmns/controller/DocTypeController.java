package com.project.adminmns.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.adminmns.dao.DocTypeDao;
import com.project.adminmns.model.DocType;
import com.project.adminmns.security.AdminPermission;
import com.project.adminmns.service.DocTypeService;
import com.project.adminmns.service.DocumentService;
import com.project.adminmns.view.DocTypeView;
import com.project.adminmns.view.TrainingView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/doctype")
@RestController
@CrossOrigin
public class DocTypeController {

    private final DocTypeService docTypeService;

    @Autowired
    public DocTypeController(DocTypeService docTypeService){
        this.docTypeService = docTypeService;
    }

    @GetMapping("/list")
    @JsonView(DocTypeView.class)
    @AdminPermission
    public List<DocType> list() {
       return docTypeService.doctypeList();
    }


    @GetMapping("/{id}")
    @JsonView(DocTypeView.class)
    @AdminPermission
    public ResponseEntity<DocType> get(@PathVariable int id) {
        return docTypeService.getDocType(id);
    }

    @PostMapping
    @JsonView(DocTypeView.class)
    @AdminPermission
    public ResponseEntity<DocType> add(@Valid @RequestBody DocType newDocType) {
        return docTypeService.addDocType(newDocType);
    }

    @PutMapping("/{id}")
    @JsonView(DocTypeView.class)
    @AdminPermission
    public ResponseEntity<DocType> update(@Valid @RequestBody DocType docType, @PathVariable int id) {
        return docTypeService.updateDocType(docType, id);
    }

    @DeleteMapping("/{id}")
    @JsonView(DocTypeView.class)
    @AdminPermission
    public ResponseEntity<DocType> delete(@PathVariable int id) {
        return docTypeService.deleteDocType(id);
    }
}