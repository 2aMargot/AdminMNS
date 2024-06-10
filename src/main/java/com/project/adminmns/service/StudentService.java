package com.project.adminmns.service;

import com.project.adminmns.dao.StudentDao;
import com.project.adminmns.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StudentService {

    StudentDao studentDao;

    public List<Student> studentList() {

        return studentDao.findAll();
    }

    public ResponseEntity<Student> getStudent(int id) {

        Optional<Student> studentOptional = studentDao.findById(id);

        if (studentOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(studentOptional.get(), HttpStatus.OK);

    }

    public ResponseEntity<Student> addStudent(Student newStudent) {

        if (newStudent.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        studentDao.save(newStudent);
        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
    }

    public ResponseEntity<Student> updateStudent(Student student, int id) {


        Optional<Student> studentOptional = studentDao.findById(student.getId());

        if (studentOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        student.setId(id);
        student.setPassword(studentOptional.get().getPassword());

        studentDao.save(student);
        return new ResponseEntity<>(studentOptional.get(), HttpStatus.OK);
    }

    public ResponseEntity<Student> deleteStudent (int id) {

        Optional<Student> studentOptional = studentDao.findById(id);

        if (studentOptional.isPresent() && studentOptional.isPresent()) {

            studentDao.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

