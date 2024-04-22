package com.project.adminmns.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.adminmns.dao.TrainingDao;
import com.project.adminmns.model.Training;
import com.project.adminmns.security.AdminPermission;
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

    protected TrainingDao trainingDao;

    @GetMapping("/training/list")
    @JsonView(TrainingView.class)
    @AdminPermission
    public List<Training> liste(){
        return trainingDao.findAll();
    }


    @GetMapping("/training/{id}")
    @JsonView(TrainingView.class)
    @AdminPermission
    public ResponseEntity<Training> get(@PathVariable int id) {

        Optional<Training> trainingOptional = this.trainingDao.findById(id);

        if(trainingOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(trainingOptional.get(), HttpStatus.OK);
    }

    @PostMapping("/training")
    @JsonView(TrainingView.class)
    @AdminPermission
    public ResponseEntity<Training> add(@Valid @RequestBody Training newTraining) {

        //mise à jour
        if (newTraining.getId() != null) {
            Optional<Training> trainingOptional = this.trainingDao.findById(newTraining.getId());

            // l'utilisateur tente de modifier un training qui n'existe pas ou plus
            if (trainingOptional.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            this.trainingDao.save(newTraining);

            return new ResponseEntity<>(trainingOptional.get(), HttpStatus.OK);
        }

        trainingDao.save(newTraining);

        return new ResponseEntity<>(newTraining, HttpStatus.CREATED);
    }

    @PutMapping("/training/{id}")
    @JsonView(TrainingView.class)
    @AdminPermission
    public ResponseEntity<Training> update (@Valid @RequestBody Training training, @PathVariable int id) {
        training.setId(id);

        Optional<Training> trainingOptional = trainingDao.findById(training.getId());

        //l'utilisateur tente de modifier un training qui n'existe pas/plus
        if(trainingOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.trainingDao.save(training);
        return new ResponseEntity<>(trainingOptional.get(), HttpStatus.OK);
    }
    @DeleteMapping("/training/{id}")
    @JsonView(TrainingView.class)
    @AdminPermission
    public ResponseEntity<Training> delete(@PathVariable int id){

        Optional<Training> trainingOptional = this.trainingDao.findById(id);

        if(trainingOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        trainingDao.deleteById(id);
        return new ResponseEntity<>(trainingOptional.get(),HttpStatus.OK);
    }
}