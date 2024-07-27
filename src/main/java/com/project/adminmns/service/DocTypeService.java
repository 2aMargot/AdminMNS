package com.project.adminmns.service;

import com.project.adminmns.dao.DocTypeDao;
import com.project.adminmns.model.DocType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing document types.
 * <p>
 * This service provides methods for retrieving, adding, updating, and deleting {@link DocType} entities.
 * </p>
 */
@Service
public class DocTypeService {

    private final DocTypeDao docTypeDao;

    /**
     * Constructs a {@link DocTypeService} with the specified {@link DocTypeDao}.
     *
     * @param docTypeDao The {@link DocTypeDao} used to perform CRUD operations on {@link DocType} entities.
     */
    @Autowired
    public DocTypeService(DocTypeDao docTypeDao){
        this.docTypeDao = docTypeDao;
    }

    /**
     * Retrieves a list of all {@link DocType} entities.
     *
     * @return A list of {@link DocType} objects.
     */
    public List<DocType> doctypeList() {
        return docTypeDao.findAll();
    }

    /**
     * Retrieves a {@link DocType} entity by its ID.
     *
     * @param id The ID of the {@link DocType} to retrieve.
     * @return A {@link ResponseEntity} containing the {@link DocType} if found, or {@link HttpStatus#NOT_FOUND} if not found.
     */
    public ResponseEntity<DocType> getDocType(int id) {

        Optional<DocType> docTypeOptional = this.docTypeDao.findById(id);

        if (docTypeOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(docTypeOptional.get(), HttpStatus.OK);
    }

    /**
     * Adds a new {@link DocType} entity.
     *
     * @param newDocType The {@link DocType} object to add.
     * @return A {@link ResponseEntity} containing the added {@link DocType} if the ID is null, or {@link HttpStatus#BAD_REQUEST} if the ID is not null.
     */
    public ResponseEntity<DocType> addDocType(DocType newDocType) {

        if (newDocType.getId() != null) {
            Optional<DocType> docTypeOptional = this.docTypeDao.findById(newDocType.getId());


            if (docTypeOptional.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            this.docTypeDao.save(newDocType);
            return new ResponseEntity<>(docTypeOptional.get(), HttpStatus.OK);
        }

        docTypeDao.save(newDocType);
        return new ResponseEntity<>(newDocType, HttpStatus.CREATED);
    }

    /**
     * Updates an existing {@link DocType} entity.
     *
     * @param docType The {@link DocType} object containing the updated details.
     * @param id The ID of the {@link DocType} to update.
     * @return A {@link ResponseEntity} containing the updated {@link DocType} if the entity exists, or {@link HttpStatus#BAD_REQUEST} if the entity does not exist.
     */
    public ResponseEntity<DocType> updateDocType(DocType docType, int id) {

        Optional<DocType> docTypeOptional = docTypeDao.findById(docType.getId());

        if (docTypeOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        docType.setId(id);
        this.docTypeDao.save(docType);
        return new ResponseEntity<>(docTypeOptional.get(), HttpStatus.OK);
    }

    /**
     * Deletes a {@link DocType} entity by its ID.
     *
     * @param id The ID of the {@link DocType} to delete.
     * @return A {@link ResponseEntity} with {@link HttpStatus#OK} if the deletion was successful, or {@link HttpStatus#NOT_FOUND} if the entity does not exist.
     */
    public ResponseEntity<DocType> deleteDocType(int id) {

        Optional<DocType> docTypeOptional = this.docTypeDao.findById(id);

        if (docTypeOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        docTypeDao.deleteById(id);
        return new ResponseEntity<>(docTypeOptional.get(), HttpStatus.OK);
    }
}
