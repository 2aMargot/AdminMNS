package com.project.adminmns.service;

import com.project.adminmns.dao.StudentDao;
import com.project.adminmns.model.Student;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class StudentService {

    StudentDao studentDao;

    public List<Student> studentList() {

        return studentDao.findAll();
    }

    public ResponseEntity<Student> getStudent(@PathVariable int id) {

        Optional<Student> studentOptional = studentDao.findById(id);

        if (studentOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(studentOptional.get(), HttpStatus.OK);

    }

    public ResponseEntity<Student> addStudent(@Valid @RequestBody Student newStudent) {

        if (newStudent.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        studentDao.save(newStudent);
        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
    }

    public ResponseEntity<Student> updateStudent(@Valid @RequestBody Student student, @PathVariable int id) {
        student.setId(id);

        Optional<Student> studentOptional = studentDao.findById(student.getId());

        if (studentOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        student.setPassword(studentOptional.get().getPassword());

        studentDao.save(student);
        return new ResponseEntity<>(studentOptional.get(), HttpStatus.OK);
    }

    public ResponseEntity<Student> deleteStudent(@PathVariable int id) {

        Optional<Student> studentOptional = studentDao.findById(id);

        if (studentOptional.isPresent() && studentOptional.isPresent()) {

            studentDao.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

