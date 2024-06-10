package com.project.adminmns.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.adminmns.dao.*;
import com.project.adminmns.model.Student;
import com.project.adminmns.security.AdminPermission;
import com.project.adminmns.service.StudentService;
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

    StudentService studentService;

    @GetMapping("/student/list")
    @JsonView(StudentView.class)
    @AdminPermission
    public List<Student> list() {

        return studentService.studentList();
    }

    @GetMapping("/student/{id}")
    @AdminPermission
    @JsonView(StudentView.class)
    public ResponseEntity<Student> get(@PathVariable int id) {

        return studentService.getStudent(id);
    }

    @PostMapping("/student")
    @AdminPermission
    @JsonView(StudentView.class)
    public ResponseEntity<Student> add(@Valid @RequestBody Student newStudent) {

        return studentService.addStudent(newStudent);
    }

    @PutMapping("/student/{id}")
    @AdminPermission
    @JsonView(StudentView.class)
    public ResponseEntity<Student> update(@Valid @RequestBody Student student, @PathVariable int id) {

        return studentService.updateStudent(student, id);
    }

    @DeleteMapping("/student/{id}")
    @AdminPermission
    @JsonView(StudentView.class)
    public ResponseEntity<Student> delete(@PathVariable int id) {
        return studentService.deleteStudent(id);
    }
}
