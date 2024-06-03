package com.project.adminmns.service;

import com.project.adminmns.dao.AbsenceDao;
import com.project.adminmns.model.Absence;
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
public class AbsenceService {

    @Autowired
    protected AbsenceDao absenceDao;

    public List<Absence> AbsenceList() {
        return absenceDao.findAll();
    }

    public ResponseEntity<Absence> getAbsence(@PathVariable int id) {

        Optional<Absence> absenceOptional = this.absenceDao.findById(id);

        if (absenceOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(absenceOptional.get(), HttpStatus.OK);
    }

    public ResponseEntity<Absence> addAbsence(@Valid @RequestBody Absence newAbsence) {

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

    public ResponseEntity<Absence> updateAbsence(@Valid @RequestBody Absence absence, @PathVariable int id) {
        absence.setId(id);

        Optional<Absence> absenceOptional = absenceDao.findById(absence.getId());

        //l'utilisateur tente de modifier un absence qui n'existe pas/plus
        if (absenceOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.absenceDao.save(absence);
        return new ResponseEntity<>(absenceOptional.get(), HttpStatus.OK);
    }

    public ResponseEntity<Absence> deleteAbsence(@PathVariable int id) {

        Optional<Absence> absenceOptional = this.absenceDao.findById(id);

        if (absenceOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        absenceDao.deleteById(id);
        return new ResponseEntity<>(absenceOptional.get(), HttpStatus.OK);
    }

}
