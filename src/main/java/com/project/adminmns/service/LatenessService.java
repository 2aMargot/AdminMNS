package com.project.adminmns.service;

import com.project.adminmns.dao.LatenessDao;
import com.project.adminmns.model.Lateness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing lateness records.
 * <p>
 * This service provides methods for retrieving, adding, updating, and deleting {@link Lateness} entities.
 * </p>
 */
@Service
public class LatenessService {

    LatenessDao latenessDao;

    /**
     * Constructs a {@link LatenessService} with the specified {@link LatenessDao}.
     *
     * @param latenessDao The {@link LatenessDao} used to perform CRUD operations on {@link Lateness} entities.
     */
    @Autowired
    public LatenessService(LatenessDao latenessDao){
        this.latenessDao = latenessDao;
    }

    /**
     * Retrieves a list of all {@link Lateness} entities.
     *
     * @return A list of {@link Lateness} objects.
     */
    public List<Lateness> latenessList() {

        return latenessDao.findAll();
    }

    /**
     * Retrieves a {@link Lateness} entity by its ID.
     *
     * @param id The ID of the {@link Lateness} to retrieve.
     * @return A {@link ResponseEntity} containing the {@link Lateness} if found, or {@link HttpStatus#NOT_FOUND} if not found.
     */
    public ResponseEntity<Lateness> getLateness(int id) {

        Optional<Lateness> latenessOptional = this.latenessDao.findById(id);

        if (latenessOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(latenessOptional.get(), HttpStatus.OK);
    }

    /**
     * Adds a new {@link Lateness} entity.
     *
     * @param newLateness The {@link Lateness} object to add.
     * @return A {@link ResponseEntity} containing the added {@link Lateness} if the ID is null, or {@link HttpStatus#BAD_REQUEST} if the ID is not null.
     */
    public ResponseEntity<Lateness> addLateness(Lateness newLateness) {

        if (newLateness.getId() != null) {
            Optional<Lateness> latenessOptional = this.latenessDao.findById(newLateness.getId());

            if (latenessOptional.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            this.latenessDao.save(newLateness);

            return new ResponseEntity<>(latenessOptional.get(), HttpStatus.OK);
        }

        latenessDao.save(newLateness);

        return new ResponseEntity<>(newLateness, HttpStatus.CREATED);
    }

    /**
     * Updates an existing {@link Lateness} entity.
     *
     * @param lateness The {@link Lateness} object containing the updated details.
     * @param id The ID of the {@link Lateness} to update.
     * @return A {@link ResponseEntity} containing the updated {@link Lateness} if the entity exists, or {@link HttpStatus#BAD_REQUEST} if the entity does not exist.
     */
    public ResponseEntity<Lateness> updateLateness(Lateness lateness, int id) {

        Optional<Lateness> latenessOptional = latenessDao.findById(lateness.getId());

        if (latenessOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        lateness.setId(id);
        this.latenessDao.save(lateness);
        return new ResponseEntity<>(latenessOptional.get(), HttpStatus.OK);
    }

    /**
     * Deletes a {@link Lateness} entity by its ID.
     *
     * @param id The ID of the {@link Lateness} to delete.
     * @return A {@link ResponseEntity} with {@link HttpStatus#OK} if the deletion was successful, or {@link HttpStatus#NOT_FOUND} if the entity does not exist.
     */
    public ResponseEntity<Lateness> deleteLateness(int id) {

        Optional<Lateness> latenessOptional = this.latenessDao.findById(id);

        if (latenessOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        latenessDao.deleteById(id);
        return new ResponseEntity<>(latenessOptional.get(), HttpStatus.OK);
    }

}
