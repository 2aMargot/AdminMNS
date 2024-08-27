package com.project.adminmns.service;

import com.project.adminmns.dao.TrainingDao;
import com.project.adminmns.model.Training;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing {@link Training} entities.
 * <p>
 * This service provides methods for performing CRUD operations on {@link Training} entities.
 * It uses {@link TrainingDao} for data access operations.
 * </p>
 */
@Service
public class TrainingService {

    private final TrainingDao trainingDao;

    /**
     * Constructs a TrainingService with the specified {@link TrainingDao}.
     *
     * @param trainingDao The {@link TrainingDao} used to perform CRUD operations on {@link Training} entities.
     */
    public TrainingService(TrainingDao trainingDao){
        this.trainingDao = trainingDao;
    }

    /**
     * Retrieves a list of all {@link Training} entities.
     *
     * @return A list of {@link Training} objects.
     */
    public List<Training> trainingList() {
        return trainingDao.findAll();
    }

    /**
     * Retrieves a {@link Training} entity by ID.
     *
     * @param id The ID of the {@link Training} to retrieve.
     * @return A {@link ResponseEntity} containing the {@link Training} if found, or {@link HttpStatus#NOT_FOUND} if not found.
     */
    public ResponseEntity<Training> getTraining(int id) {

        Optional<Training> trainingOptional = this.trainingDao.findById(id);

        if (trainingOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(trainingOptional.get(), HttpStatus.OK);
    }

    /**
     * Adds a new {@link Training} entity.
     *
     * @param newTraining The {@link Training} object to add.
     * @return A {@link ResponseEntity} containing the added {@link Training} if the ID is null,
     * or {@link HttpStatus#OK} if the training ID is already present in the database.
     */
    public ResponseEntity<Training> addTraining(Training newTraining) {

        if (newTraining.getId() != null) {
            Optional<Training> trainingOptional = this.trainingDao.findById(newTraining.getId());


            if (trainingOptional.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            this.trainingDao.save(newTraining);

            return new ResponseEntity<>(trainingOptional.get(), HttpStatus.OK);
        }

        trainingDao.save(newTraining);

        return new ResponseEntity<>(newTraining, HttpStatus.CREATED);
    }

    /**
     * Updates an existing {@link Training} entity.
     *
     * @param training The {@link Training} object containing the updated details.
     * @param id The ID of the {@link Training} to update.
     * @return A {@link ResponseEntity} containing the updated {@link Training} if the entity exists,
     * or {@link HttpStatus#BAD_REQUEST} if the entity does not exist.
     */
    public ResponseEntity<Training> updateTraining(@Valid @RequestBody Training training, @PathVariable int id) {

        Optional<Training> trainingOptional = trainingDao.findById(training.getId());

        if (trainingOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        training.setId(id);
        this.trainingDao.save(training);
        return new ResponseEntity<>(trainingOptional.get(), HttpStatus.OK);
    }

    /**
     * Deletes a {@link Training} entity by its ID.
     *
     * @param id The ID of the {@link Training} to delete.
     * @return A {@link ResponseEntity} with {@link HttpStatus#OK} if the entity was deleted,
     * or {@link HttpStatus#NOT_FOUND} if the entity does not exist.
     */
    public ResponseEntity<Training> deleteTraining(@PathVariable int id) {

        Optional<Training> trainingOptional = this.trainingDao.findById(id);

        if (trainingOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        trainingDao.deleteById(id);
        return new ResponseEntity<>(trainingOptional.get(), HttpStatus.OK);
    }
}
