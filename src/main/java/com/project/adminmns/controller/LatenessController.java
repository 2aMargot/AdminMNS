package com.project.adminmns.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.adminmns.dao.LatenessDao;
import com.project.adminmns.model.Lateness;
import com.project.adminmns.security.AdminPermission;
import com.project.adminmns.service.LatenessService;
import com.project.adminmns.view.LatenessView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@AllArgsConstructor
public class LatenessController {

    @Autowired
    LatenessService latenessService;

    @GetMapping("/lateness/list")
    @JsonView(LatenessView.class)
    @AdminPermission
    public List<Lateness> list() {

        return latenessService.latenessList();
    }


    @GetMapping("/lateness/{id}")
    @JsonView(LatenessView.class)
    @AdminPermission
    public ResponseEntity<Lateness> get(@PathVariable int id) {

        return latenessService.getLateness(id);
    }

    @PostMapping("/lateness")
    @JsonView(LatenessView.class)
    @AdminPermission
    public ResponseEntity<Lateness> add(@Valid @RequestBody Lateness newLateness) {

        return latenessService.addLateness(newLateness);
    }
    @PutMapping("/lateness/{id}")
    @JsonView(LatenessView.class)
    @AdminPermission
    public ResponseEntity<Lateness> update(@Valid @RequestBody Lateness lateness, @PathVariable int id) {

        return latenessService.updateLateness(lateness, id);
    }

    @DeleteMapping("/lateness/{id}")
    @JsonView(LatenessView.class)
    @AdminPermission
    public ResponseEntity<Lateness> delete(@PathVariable int id) {

        return latenessService.deleteLateness(id);
    }
}

