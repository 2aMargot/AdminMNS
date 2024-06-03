package com.project.adminmns.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.adminmns.dao.TrainingDao;
import com.project.adminmns.model.Training;
import com.project.adminmns.security.AdminPermission;
import com.project.adminmns.service.TrainingService;
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
public class TrainingController {

    TrainingService trainingService;

    @GetMapping("/training/list")
    @JsonView(TrainingView.class)
    @AdminPermission
    public List<Training> list() {
        return trainingService.trainingList();
    }

    @GetMapping("/training/{id}")
    @JsonView(TrainingView.class)
    @AdminPermission
    public ResponseEntity<Training> get(@PathVariable int id) {

        return trainingService.getTraining(id);
    }

    @PostMapping("/training")
    @JsonView(TrainingView.class)
    @AdminPermission
    public ResponseEntity<Training> add(@Valid @RequestBody Training newTraining) {

        return trainingService.addTraining(newTraining);
    }

    @PutMapping("/training/{id}")
    @JsonView(TrainingView.class)
    @AdminPermission
    public ResponseEntity<Training> update(@Valid @RequestBody Training training, @PathVariable int id) {

        return trainingService.updateTraining(training, id);
    }

    @DeleteMapping("/training/{id}")
    @JsonView(TrainingView.class)
    @AdminPermission
    public ResponseEntity<Training> delete(@PathVariable int id) {

        return trainingService.deleteTraining(id);
    }
}