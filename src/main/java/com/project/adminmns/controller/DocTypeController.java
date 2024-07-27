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

    /**
     * Constructs a DocTypeController with the specified DocTypeService.
     *
     * @param docTypeService The DocTypeService object used to handle document type-related operations.
     */
    @Autowired
    public DocTypeController(DocTypeService docTypeService){
        this.docTypeService = docTypeService;
    }

    /**
     * Retrieves a list of all document types.
     *
     * @return A list of DocType objects.
     */
    @GetMapping("/list")
    @JsonView(DocTypeView.class)
    @AdminPermission
    public List<DocType> list() {
       return docTypeService.doctypeList();
    }


    /**
     * Retrieves a document type by its ID.
     *
     * @param id The ID of the document type to retrieve.
     * @return A ResponseEntity containing the DocType object if found, or an appropriate HTTP status.
     */
    @GetMapping("/{id}")
    @JsonView(DocTypeView.class)
    @AdminPermission
    public ResponseEntity<DocType> get(@PathVariable int id) {
        return docTypeService.getDocType(id);
    }

    /**
     * Adds a new document type.
     *
     * @param newDocType The DocType object containing the details of the new document type to add.
     * @return A ResponseEntity containing the added DocType object, or an appropriate HTTP status.
     */
    @PostMapping
    @JsonView(DocTypeView.class)
    @AdminPermission
    public ResponseEntity<DocType> add(@Valid @RequestBody DocType newDocType) {
        return docTypeService.addDocType(newDocType);
    }

    /**
     * Updates an existing document type.
     *
     * @param docType The DocType object containing the updated document type details.
     * @param id The ID of the document type to update.
     * @return A ResponseEntity containing the updated DocType object, or an appropriate HTTP status.
     */
    @PutMapping("/{id}")
    @JsonView(DocTypeView.class)
    @AdminPermission
    public ResponseEntity<DocType> update(@Valid @RequestBody DocType docType, @PathVariable int id) {
        return docTypeService.updateDocType(docType, id);
    }

    /**
     * Deletes an existing document type by its ID.
     *
     * @param id The ID of the document type to delete.
     * @return A ResponseEntity containing the deleted DocType object if successful, or an appropriate HTTP status.
     */
    @DeleteMapping("/{id}")
    @JsonView(DocTypeView.class)
    @AdminPermission
    public ResponseEntity<DocType> delete(@PathVariable int id) {
        return docTypeService.deleteDocType(id);
    }
}