package com.project.adminmns.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.adminmns.dao.AbsenceDao;
import com.project.adminmns.model.Absence;
import com.project.adminmns.security.AdminPermission;
import com.project.adminmns.view.AbsenceView;
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
public class AbsenceController {

    protected AbsenceDao absenceDao;

    @GetMapping("/absence/list")
    @JsonView(AbsenceView.class)
    @AdminPermission
    public List<Absence> liste() {
        return absenceDao.findAll();
    }


    @GetMapping("/absence/{id}")
    @JsonView(AbsenceView.class)
    @AdminPermission
    public ResponseEntity<Absence> get(@PathVariable int id) {

        Optional<Absence> absenceOptional = this.absenceDao.findById(id);

        if (absenceOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(absenceOptional.get(), HttpStatus.OK);
    }

    @PostMapping("/absence")
    @JsonView(AbsenceView.class)
    @AdminPermission
    public ResponseEntity<Absence> add(@Valid @RequestBody Absence newAbsence) {

        //mise à jour
        if (newAbsence.getId() != null) {
            Optional<Absence> absenceOptional = this.absenceDao.findById(newAbsence.getId());

            // l'utilisateur tente de modifier un absence qui n'existe pas ou plus
            if (absenceOptional.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            this.absenceDao.save(newAbsence);

            return new ResponseEntity<>(absenceOptional.get(), HttpStatus.OK);
        }

        absenceDao.save(newAbsence);

        return new ResponseEntity<>(newAbsence, HttpStatus.CREATED);
    }

    @PutMapping("/absence/{id}")
    @JsonView(AbsenceView.class)
    @AdminPermission
    public ResponseEntity<Absence> update(@Valid @RequestBody Absence absence, @PathVariable int id) {
        absence.setId(id);

        Optional<Absence> absenceOptional = absenceDao.findById(absence.getId());

        //l'utilisateur tente de modifier un absence qui n'existe pas/plus
        if (absenceOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.absenceDao.save(absence);
        return new ResponseEntity<>(absenceOptional.get(), HttpStatus.OK);
    }

    @DeleteMapping("/absence/{id}")
    @JsonView(AbsenceView.class)
    @AdminPermission
    public ResponseEntity<Absence> delete(@PathVariable int id) {

        Optional<Absence> absenceOptional = this.absenceDao.findById(id);

        if (absenceOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        absenceDao.deleteById(id);
        return new ResponseEntity<>(absenceOptional.get(), HttpStatus.OK);
    }
}