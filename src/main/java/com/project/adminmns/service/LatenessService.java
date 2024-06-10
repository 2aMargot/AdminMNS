package com.project.adminmns.service;

import com.project.adminmns.dao.LatenessDao;
import com.project.adminmns.model.Lateness;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class LatenessService {

    LatenessDao latenessDao;

    @Autowired
    public LatenessService(LatenessDao latenessDao){
        this.latenessDao = latenessDao;
    }

    public List<Lateness> latenessList() {

        return latenessDao.findAll();
    }

    public ResponseEntity<Lateness> getLateness(int id) {

        Optional<Lateness> latenessOptional = this.latenessDao.findById(id);

        if (latenessOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(latenessOptional.get(), HttpStatus.OK);
    }

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

    public ResponseEntity<Lateness> updateLateness(Lateness lateness, int id) {

        Optional<Lateness> latenessOptional = latenessDao.findById(lateness.getId());

        if (latenessOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        lateness.setId(id);
        this.latenessDao.save(lateness);
        return new ResponseEntity<>(latenessOptional.get(), HttpStatus.OK);
    }

    public ResponseEntity<Lateness> deleteLateness(int id) {

        Optional<Lateness> latenessOptional = this.latenessDao.findById(id);

        if (latenessOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        latenessDao.deleteById(id);
        return new ResponseEntity<>(latenessOptional.get(), HttpStatus.OK);
    }

}
