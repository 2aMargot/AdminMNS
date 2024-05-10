package com.project.adminmns.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.adminmns.dao.LatenessDao;
import com.project.adminmns.model.Lateness;
import com.project.adminmns.security.AdminPermission;
import com.project.adminmns.view.LatenessView;
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
public class LatenessController {

    protected LatenessDao latenessDao;

    @GetMapping("/lateness/list")
    @JsonView(LatenessView.class)
    @AdminPermission
    public List<Lateness> liste() {
        return latenessDao.findAll();
    }


    @GetMapping("/lateness/{id}")
    @JsonView(LatenessView.class)
    @AdminPermission
    public ResponseEntity<Lateness> get(@PathVariable int id) {

        Optional<Lateness> latenessOptional = this.latenessDao.findById(id);

        if (latenessOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(latenessOptional.get(), HttpStatus.OK);
    }

    @PostMapping("/lateness")
    @JsonView(LatenessView.class)
    @AdminPermission
    public ResponseEntity<Lateness> add(@Valid @RequestBody Lateness newLateness) {

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

    @PutMapping("/lateness/{id}")
    @JsonView(LatenessView.class)
    @AdminPermission
    public ResponseEntity<Lateness> update(@Valid @RequestBody Lateness lateness, @PathVariable int id) {
        lateness.setId(id);

        Optional<Lateness> latenessOptional = latenessDao.findById(lateness.getId());

        //l'utilisateur tente de modifier un lateness qui n'existe pas/plus
        if (latenessOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.latenessDao.save(lateness);
        return new ResponseEntity<>(latenessOptional.get(), HttpStatus.OK);
    }

    @DeleteMapping("/lateness/{id}")
    @JsonView(LatenessView.class)
    @AdminPermission
    public ResponseEntity<Lateness> delete(@PathVariable int id) {

        Optional<Lateness> latenessOptional = this.latenessDao.findById(id);

        if (latenessOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        latenessDao.deleteById(id);
        return new ResponseEntity<>(latenessOptional.get(), HttpStatus.OK);
    }
}

