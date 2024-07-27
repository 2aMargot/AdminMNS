package com.project.adminmns.service;

import com.project.adminmns.dao.DocumentDao;
import com.project.adminmns.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing {@link Document} entities.
 * <p>
 * This service provides methods for performing CRUD operations on {@link Document} entities.
 * It uses {@link DocumentDao} for data access operations.
 * </p>
 */
@Service
public class DocumentService {

    private final DocumentDao documentDao;

    /**
     * Constructs a DocumentService with the specified {@link DocumentDao}.
     *
     * @param documentDao The {@link DocumentDao} used to perform CRUD operations on {@link Document} entities.
     */
    @Autowired
    public DocumentService(DocumentDao documentDao){
        this.documentDao = documentDao;
    }

    /**
     * Retrieves a list of all {@link Document} entities.
     *
     * @return A list of {@link Document} objects.
     */
    public List<Document> documentList() {
        return documentDao.findAll();
    }

    /**
     * Retrieves a {@link Document} entity by its ID.
     *
     * @param id The ID of the {@link Document} to retrieve.
     * @return A {@link ResponseEntity} containing the {@link Document} if found, or {@link HttpStatus#NOT_FOUND} if not found.
     */
    public ResponseEntity<Document> getDocument(int id) {

        Optional<Document> documentOptional = this.documentDao.findById(id);

        if (documentOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(documentOptional.get(), HttpStatus.OK);
    }

    /**
     * Adds a new {@link Document} entity.
     *
     * @param newDocument The {@link Document} object to add.
     * @return A {@link ResponseEntity} containing the added {@link Document} if the ID is null,
     * or {@link HttpStatus#OK} if the document ID is already present in the database.
     */
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

    /**
     * Updates an existing {@link Document} entity.
     *
     * @param document The {@link Document} object containing the updated details.
     * @param id The ID of the {@link Document} to update.
     * @return A {@link ResponseEntity} containing the updated {@link Document} if the entity exists, or {@link HttpStatus#BAD_REQUEST} if the entity does not exist.
     */
    public ResponseEntity<Document> updateDocument(Document document, int id) {

        Optional<Document> documentOptional = documentDao.findById(document.getId());

        if (documentOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        document.setId(id);
        this.documentDao.save(document);
        return new ResponseEntity<>(documentOptional.get(), HttpStatus.OK);
    }

    /**
     * Deletes a {@link Document} entity by its ID.
     *
     * @param id The ID of the {@link Document} to delete.
     * @return A {@link ResponseEntity} with {@link HttpStatus#OK} if the entity was deleted, or {@link HttpStatus#NOT_FOUND} if the entity does not exist.
     */
    public ResponseEntity<Document> deleteDocument(int id) {

        Optional<Document> documentOptional = this.documentDao.findById(id);

        if (documentOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.documentDao.deleteById(id);
        return new ResponseEntity<>(documentOptional.get(), HttpStatus.OK);
    }

}
