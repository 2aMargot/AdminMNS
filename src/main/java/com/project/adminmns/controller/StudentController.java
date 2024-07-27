package com.project.adminmns.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.adminmns.dao.*;
import com.project.adminmns.model.Student;
import com.project.adminmns.security.AdminPermission;
import com.project.adminmns.service.StudentService;
import com.project.adminmns.view.StudentView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/student")
@RestController
@CrossOrigin
public class StudentController {

    private final StudentService studentService;

    /**
     * Constructs a StudentController with the specified StudentService.
     *
     * @param studentService The StudentService object used to handle student-related operations.
     */
    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    /**
     * Retrieves a list of all students.
     *
     * @return A list of Student objects.
     */
    @GetMapping("/list")
    @JsonView(StudentView.class)
    @AdminPermission
    public List<Student> list() {

        return studentService.studentList();
    }

    /**
     * Retrieves a student by their ID.
     *
     * @param id The ID of the student to retrieve.
     * @return A ResponseEntity containing the Student object if found, or an appropriate HTTP status.
     */
    @GetMapping("/{id}")
    @AdminPermission
    @JsonView(StudentView.class)
    public ResponseEntity<Student> get(@PathVariable int id) {

        return studentService.getStudent(id);
    }

    /**
     * Adds a new student.
     *
     * @param newStudent The Student object containing the details of the new student to add.
     * @return A ResponseEntity containing the added Student object, or an appropriate HTTP status.
     */
    @PostMapping
    @AdminPermission
    @JsonView(StudentView.class)
    public ResponseEntity<Student> add(@Valid @RequestBody Student newStudent) {

        return studentService.addStudent(newStudent);
    }

    /**
     * Updates an existing student.
     *
     * @param student The Student object containing the updated student details.
     * @param id The ID of the student to update.
     * @return A ResponseEntity containing the updated Student object, or an appropriate HTTP status.
     */
    @PutMapping("/{id}")
    @AdminPermission
    @JsonView(StudentView.class)
    public ResponseEntity<Student> update(@Valid @RequestBody Student student, @PathVariable int id) {

        return studentService.updateStudent(student, id);
    }

    /**
     * Deletes an existing student by their ID.
     *
     * @param id The ID of the student to delete.
     * @return A ResponseEntity containing the deleted Student object if successful, or an appropriate HTTP status.
     */
    @DeleteMapping("/{id}")
    @AdminPermission
    @JsonView(StudentView.class)
    public ResponseEntity<Student> delete(@PathVariable int id) {
        return studentService.deleteStudent(id);
    }
}
