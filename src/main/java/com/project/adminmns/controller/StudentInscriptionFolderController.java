package com.project.adminmns.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.adminmns.dao.StudentInscriptionFolderDao;
import com.project.adminmns.model.StudentInscriptionFolder;
import com.project.adminmns.security.AdminPermission;
import com.project.adminmns.view.StudentInscriptionFolderView;
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
public class StudentInscriptionFolderController {

    StudentInscriptionFolderDao studentInscriptionFolderDao;



    @GetMapping("/student-inscription-folder/{id}")
    @AdminPermission
    @JsonView(StudentInscriptionFolderView.class)
    public ResponseEntity<StudentInscriptionFolder> get(@PathVariable int id) {

        Optional<StudentInscriptionFolder> studentInscriptionFolderOptional = studentInscriptionFolderDao.findById(id);

        if (studentInscriptionFolderOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(studentInscriptionFolderOptional.get(), HttpStatus.OK);

    }

    @GetMapping("/student-inscription-folder/list")
    @JsonView(StudentInscriptionFolderView.class)
    @AdminPermission
    public List<StudentInscriptionFolder> list() {
        return studentInscriptionFolderDao.findAll();
    }

    @PostMapping("/student-inscription-folder")
    @AdminPermission
    @JsonView(StudentInscriptionFolderView.class)
    public ResponseEntity<StudentInscriptionFolder> add(@Valid @RequestBody StudentInscriptionFolder newStudentInscriptionFolder) {

        if (newStudentInscriptionFolder.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        studentInscriptionFolderDao.save(newStudentInscriptionFolder);
        return new ResponseEntity<>(newStudentInscriptionFolder, HttpStatus.CREATED);
    }

    @PutMapping("/student-inscription-folder/{id}")
    @AdminPermission
    @JsonView(StudentInscriptionFolderView.class)
    public ResponseEntity<StudentInscriptionFolder> modified(@Valid @RequestBody StudentInscriptionFolder studentInscriptionFolder, @PathVariable int id) {
        studentInscriptionFolder.setId(id);

        Optional<StudentInscriptionFolder> studentInscriptionFolderOptional = studentInscriptionFolderDao.findById(studentInscriptionFolder.getId());

        if (studentInscriptionFolderOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        studentInscriptionFolderDao.save(studentInscriptionFolder);
        return new ResponseEntity<>(studentInscriptionFolderOptional.get(), HttpStatus.OK);
    }

    @DeleteMapping("/student-inscription-folder/{id}")
    @AdminPermission
    @JsonView(StudentInscriptionFolderView.class)
    public ResponseEntity<StudentInscriptionFolder> delete(@PathVariable int id) {

        Optional<StudentInscriptionFolder> studentInscriptionFolderOptional = studentInscriptionFolderDao.findById(id);

        if (studentInscriptionFolderOptional.isPresent()) {
            studentInscriptionFolderDao.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
