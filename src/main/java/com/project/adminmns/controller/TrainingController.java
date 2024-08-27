package com.project.adminmns.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.adminmns.model.Training;
import com.project.adminmns.security.AdminPermission;
import com.project.adminmns.service.TrainingService;
import com.project.adminmns.view.TrainingView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/training")
@RestController
@CrossOrigin
public class TrainingController {

    private final TrainingService trainingService;

    /**
     * Constructs a TrainingController with the specified TrainingService.
     *
     * @param trainingService The TrainingService object used to handle training-related operations.
     */
    @Autowired
    public TrainingController(TrainingService trainingService){
        this.trainingService = trainingService;
    }

    /**
     * Retrieves a list of all trainings.
     *
     * @return A list of Training objects.
     */
    @GetMapping("/list")
    @JsonView(TrainingView.class)
    @AdminPermission
    public List<Training> list() {
        return trainingService.trainingList();
    }

    /**
     * Retrieves a training by its ID.
     *
     * @param id The ID of the training to retrieve.
     * @return A ResponseEntity containing the Training object if found, or an appropriate HTTP status.
     */
    @GetMapping("/{id}")
    @JsonView(TrainingView.class)
    @AdminPermission
    public ResponseEntity<Training> get(@PathVariable int id) {

        return trainingService.getTraining(id);
    }

    /**
     * Adds a new training.
     *
     * @param newTraining The Training object containing the details of the new training to add.
     * @return A ResponseEntity containing the added Training object, or an appropriate HTTP status.
     */
    @PostMapping
    @JsonView(TrainingView.class)
    @AdminPermission
    public ResponseEntity<Training> add(@Valid @RequestBody Training newTraining) {

        return trainingService.addTraining(newTraining);
    }

    /**
     * Updates an existing training.
     *
     * @param training The Training object containing the updated training details.
     * @param id The ID of the training to update.
     * @return A ResponseEntity containing the updated Training object, or an appropriate HTTP status.
     */
    @PutMapping("/{id}")
    @JsonView(TrainingView.class)
    @AdminPermission
    public ResponseEntity<Training> update(@Valid @RequestBody Training training, @PathVariable int id) {

        return trainingService.updateTraining(training, id);
    }

    /**
     * Deletes an existing training by its ID.
     *
     * @param id The ID of the training to delete.
     * @return A ResponseEntity containing the deleted Training object if successful, or an appropriate HTTP status.
     */
    @DeleteMapping("/{id}")
    @JsonView(TrainingView.class)
    @AdminPermission
    public ResponseEntity<Training> delete(@PathVariable int id) {

        return trainingService.deleteTraining(id);
    }
}