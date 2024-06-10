package com.project.adminmns.service;

import com.project.adminmns.dao.StudentInscriptionFolderDao;
import com.project.adminmns.model.StudentInscriptionFolder;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class StudentInscriptionFolderService {

    private final StudentInscriptionFolderDao studentInscriptionFolderDao;

    @Autowired
    public StudentInscriptionFolderService(StudentInscriptionFolderDao studentInscriptionFolderDao){
        this.studentInscriptionFolderDao = studentInscriptionFolderDao;
    }

    public List<StudentInscriptionFolder> folderList() {
        return studentInscriptionFolderDao.findAll();
    }

    public ResponseEntity<StudentInscriptionFolder> getFolder(int id) {

        Optional<StudentInscriptionFolder> studentInscriptionFolderOptional = studentInscriptionFolderDao.findById(id);

        if (studentInscriptionFolderOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(studentInscriptionFolderOptional.get(), HttpStatus.OK);
    }

    public ResponseEntity<StudentInscriptionFolder> addFolder(StudentInscriptionFolder newStudentInscriptionFolder) {

        if (newStudentInscriptionFolder.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        studentInscriptionFolderDao.save(newStudentInscriptionFolder);
        return new ResponseEntity<>(newStudentInscriptionFolder, HttpStatus.CREATED);
    }

    public ResponseEntity<StudentInscriptionFolder> updateFolder(StudentInscriptionFolder studentInscriptionFolder, int id) {

        Optional<StudentInscriptionFolder> studentInscriptionFolderOptional = studentInscriptionFolderDao.findById(studentInscriptionFolder.getId());

        if (studentInscriptionFolderOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        studentInscriptionFolder.setId(id);
        studentInscriptionFolderDao.save(studentInscriptionFolder);
        return new ResponseEntity<>(studentInscriptionFolderOptional.get(), HttpStatus.OK);
    }

    public ResponseEntity<StudentInscriptionFolder> deleteFolder(int id) {

        Optional<StudentInscriptionFolder> studentInscriptionFolderOptional = studentInscriptionFolderDao.findById(id);

        if (studentInscriptionFolderOptional.isPresent()) {
            studentInscriptionFolderDao.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
