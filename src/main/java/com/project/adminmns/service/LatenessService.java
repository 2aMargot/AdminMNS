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

@Data
@Service
public class LatenessService {

    @Autowired
    LatenessDao latenessDao;

    public List<Lateness> latenessList() {

        return latenessDao.findAll();
    }

    public ResponseEntity<Lateness> getLateness(@PathVariable int id) {

        Optional<Lateness> latenessOptional = this.latenessDao.findById(id);

        if (latenessOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(latenessOptional.get(), HttpStatus.OK);
    }

    public ResponseEntity<Lateness> addLateness(@Valid @RequestBody Lateness newLateness) {

        //mise à jour
        if (newLateness.getId() != null) {
            Optional<Lateness> latenessOptional = this.latenessDao.findById(newLateness.getId());

            // l'utilisateur tente de modifier un lateness qui n'existe pas ou plus
            if (latenessOptional.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            this.latenessDao.save(newLateness);

            return new ResponseEntity<>(latenessOptional.get(), HttpStatus.OK);
        }

        latenessDao.save(newLateness);

        return new ResponseEntity<>(newLateness, HttpStatus.CREATED);
    }

    public ResponseEntity<Lateness> updateLateness(@Valid @RequestBody Lateness lateness, @PathVariable int id) {
        lateness.setId(id);

        Optional<Lateness> latenessOptional = latenessDao.findById(lateness.getId());

        //l'utilisateur tente de modifier un lateness qui n'existe pas/plus
        if (latenessOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.latenessDao.save(lateness);
        return new ResponseEntity<>(latenessOptional.get(), HttpStatus.OK);
    }

    public ResponseEntity<Lateness> deleteLateness(@PathVariable int id) {

        Optional<Lateness> latenessOptional = this.latenessDao.findById(id);

        if (latenessOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        latenessDao.deleteById(id);
        return new ResponseEntity<>(latenessOptional.get(), HttpStatus.OK);
    }

}
