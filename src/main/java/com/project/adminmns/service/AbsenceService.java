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

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
public class AbsenceService {

    private final AbsenceDao absenceDao;
    private final FileUploadService fileUploadService;


    @Autowired
    public AbsenceService(AbsenceDao absenceDao, FileUploadService fileUploadService){
        this.absenceDao = absenceDao;
        this.fileUploadService = fileUploadService;
    }


    public List<Absence> AbsenceList() {
        return absenceDao.findAll();
    }

    public ResponseEntity<Absence> getAbsence(int id) {

        Optional<Absence> absenceOptional = this.absenceDao.findById(id);

        if (absenceOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(absenceOptional.get(), HttpStatus.OK);
    }

    public ResponseEntity<Absence> addAbsence(Absence newAbsence) {

        if (newAbsence.getId() != null) {
            Optional<Absence> absenceOptional = this.absenceDao.findById(newAbsence.getId());

            if (absenceOptional.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            this.absenceDao.save(newAbsence);

            return new ResponseEntity<>(absenceOptional.get(), HttpStatus.OK);
        }

        absenceDao.save(newAbsence);

        return new ResponseEntity<>(newAbsence, HttpStatus.CREATED);
    }

    public ResponseEntity<Absence> updateAbsence(Absence absence, int id) {

        Optional<Absence> absenceOptional = absenceDao.findById(absence.getId());

        if (absenceOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        absence.setId(id);
        this.absenceDao.save(absence);
        return new ResponseEntity<>(absenceOptional.get(), HttpStatus.OK);
    }

    public ResponseEntity<Absence> deleteAbsence(int id) {

        Optional<Absence> absenceOptional = this.absenceDao.findById(id);

        if (absenceOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        absenceDao.deleteById(id);
        return new ResponseEntity<>(absenceOptional.get(), HttpStatus.OK);
    }

    public ResponseEntity<byte[]> uploadAbsence(InputStream inputStream, String fileName) throws IOException {

        this.fileUploadService.uploadToLocalFileSystem(inputStream, fileName);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<Byte[]> getFile(String fileName) throws IOException {

        this.fileUploadService.getFileFromUploadFolder(fileName);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}