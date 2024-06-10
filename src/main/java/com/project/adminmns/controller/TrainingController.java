package com.project.adminmns.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.adminmns.dao.TrainingDao;
import com.project.adminmns.model.Training;
import com.project.adminmns.security.AdminPermission;
import com.project.adminmns.service.TrainingService;
import com.project.adminmns.view.TrainingView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/training")
@RestController
@CrossOrigin
public class TrainingController {

    private final TrainingService trainingService;

    @Autowired
    public TrainingController(TrainingService trainingService){
        this.trainingService = trainingService;
    }

    @GetMapping("/list")
    @JsonView(TrainingView.class)
    @AdminPermission
    public List<Training> list() {
        return trainingService.trainingList();
    }

    @GetMapping("/{id}")
    @JsonView(TrainingView.class)
    @AdminPermission
    public ResponseEntity<Training> get(@PathVariable int id) {

        return trainingService.getTraining(id);
    }

    @PostMapping
    @JsonView(TrainingView.class)
    @AdminPermission
    public ResponseEntity<Training> add(@Valid @RequestBody Training newTraining) {

        return trainingService.addTraining(newTraining);
    }

    @PutMapping("/{id}")
    @JsonView(TrainingView.class)
    @AdminPermission
    public ResponseEntity<Training> update(@Valid @RequestBody Training training, @PathVariable int id) {

        return trainingService.updateTraining(training, id);
    }

    @DeleteMapping("/{id}")
    @JsonView(TrainingView.class)
    @AdminPermission
    public ResponseEntity<Training> delete(@PathVariable int id) {

        return trainingService.deleteTraining(id);
    }
}