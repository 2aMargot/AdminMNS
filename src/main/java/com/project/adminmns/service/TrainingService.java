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


@Service
public class TrainingService {

    private final TrainingDao trainingDao;

    public TrainingService(TrainingDao trainingDao){
        this.trainingDao = trainingDao;
    }

    public List<Training> trainingList() {
        return trainingDao.findAll();
    }

    public ResponseEntity<Training> getTraining(int id) {

        Optional<Training> trainingOptional = this.trainingDao.findById(id);

        if (trainingOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(trainingOptional.get(), HttpStatus.OK);
    }

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

    public ResponseEntity<Training> updateTraining(@Valid @RequestBody Training training, @PathVariable int id) {

        Optional<Training> trainingOptional = trainingDao.findById(training.getId());

        if (trainingOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        training.setId(id);
        this.trainingDao.save(training);
        return new ResponseEntity<>(trainingOptional.get(), HttpStatus.OK);
    }

    public ResponseEntity<Training> deleteTraining(@PathVariable int id) {

        Optional<Training> trainingOptional = this.trainingDao.findById(id);

        if (trainingOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        trainingDao.deleteById(id);
        return new ResponseEntity<>(trainingOptional.get(), HttpStatus.OK);
    }
}
