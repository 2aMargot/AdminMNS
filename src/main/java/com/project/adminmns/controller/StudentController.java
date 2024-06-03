package com.project.adminmns.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.adminmns.dao.*;
import com.project.adminmns.model.Student;
import com.project.adminmns.security.AdminPermission;
import com.project.adminmns.view.StudentView;
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
public class StudentController {

    StudentDao studentDao;
    LatenessDao latenessDao;
    AbsenceDao absenceDao;
    StudentInscriptionFolderDao studentInscriptionFolderDao;

    @GetMapping("/student/{id}")
    @AdminPermission
    @JsonView(StudentView.class)
    public ResponseEntity<Student> get(@PathVariable int id) {

        Optional<Student> studentOptional = studentDao.findById(id);

        if (studentOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(studentOptional.get(), HttpStatus.OK);

    }

    @GetMapping("/student/list")
    @JsonView(StudentView.class)
    @AdminPermission
    public List<Student> list() {

        return studentDao.findAll();
    }

    @PostMapping("/student")
    @AdminPermission
    @JsonView(StudentView.class)
    public ResponseEntity<Student> add(@Valid @RequestBody Student newUser) {

        if (newUser.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        studentDao.save(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/student/{id}")
    @AdminPermission
    @JsonView(StudentView.class)
    public ResponseEntity<Student> modified(@Valid @RequestBody Student user, @PathVariable int id) {
        user.setId(id);

        Optional<Student> userOptional = studentDao.findById(user.getId());

        if (userOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        user.setPassword(userOptional.get().getPassword());

        studentDao.save(user);
        return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
    }

    @DeleteMapping("/student/{id}")
    @AdminPermission
    @JsonView(StudentView.class)
    public ResponseEntity<Student> delete(@PathVariable int id) {

        Optional<Student> userOptional = studentDao.findById(id);
        Optional<Student> studentOptional = studentDao.findById(id);

        if (userOptional.isPresent() && studentOptional.isPresent()) {

            studentDao.deleteById(id);
            studentDao.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
