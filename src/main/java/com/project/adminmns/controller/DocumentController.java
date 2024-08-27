package com.project.adminmns.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.adminmns.model.Document;
import com.project.adminmns.security.AdminPermission;
import com.project.adminmns.service.DocumentService;
import com.project.adminmns.view.DocumentView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/document")
@RestController
@CrossOrigin
public class DocumentController {

    private final DocumentService documentService;

    /**
     * Constructs a DocumentController with the specified DocumentService.
     *
     * @param documentService The DocumentService object used to handle document-related operations.
     */
    @Autowired
    public DocumentController(DocumentService documentService){
        this.documentService = documentService;
    }

    /**
     * Retrieves a list of all documents.
     *
     * @return A list of Document objects.
     */
    @GetMapping("/list")
    @JsonView(DocumentView.class)
    @AdminPermission
    public List<Document> list() {
        return documentService.documentList();
    }

    /**
     * Retrieves a document by its ID.
     *
     * @param id The ID of the document to retrieve.
     * @return A ResponseEntity containing the Document object if found, or an appropriate HTTP status.
     */
    @GetMapping("/{id}")
    @JsonView(DocumentView.class)
    @AdminPermission
    public ResponseEntity<Document> get(@PathVariable int id) {
        return documentService.getDocument(id);
    }

    /**
     * Adds a new document.
     *
     * @param newDocument The Document object containing the details of the new document to add.
     * @return A ResponseEntity containing the added Document object, or an appropriate HTTP status.
     */
    @PostMapping
    @JsonView(DocumentView.class)
    @AdminPermission
    public ResponseEntity<Document> add(@Valid @RequestBody Document newDocument) {
        return documentService.addDocument(newDocument);
    }

    /**
     * Updates an existing document.
     *
     * @param document The Document object containing the updated document details.
     * @param id The ID of the document to update.
     * @return A ResponseEntity containing the updated Document object, or an appropriate HTTP status.
     */
    @PutMapping("/{id}")
    @JsonView(DocumentView.class)
    @AdminPermission
    public ResponseEntity<Document> update(@Valid @RequestBody Document document, @PathVariable int id) {
        return documentService.updateDocument(document, id);
    }

    /**
     * Deletes an existing document by its ID.
     *
     * @param id The ID of the document to delete.
     * @return A ResponseEntity containing the deleted Document object if successful, or an appropriate HTTP status.
     */
    @DeleteMapping("/{id}")
    @JsonView(DocumentView.class)
    @AdminPermission
    public ResponseEntity<Document> delete(@PathVariable int id) {
        return documentService.deleteDocument(id);
    }
}