package com.project.adminmns.service;

import com.project.adminmns.dao.StudentDao;
import com.project.adminmns.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing {@link Student} entities.
 * <p>
 * This service provides methods for performing CRUD operations on {@link Student} entities.
 * It uses {@link StudentDao} for data access operations.
 * </p>
 */
@Service
public class StudentService {

    private final StudentDao studentDao;

    /**
     * Constructs a StudentService with the specified {@link StudentDao}.
     *
     * @param studentDao The {@link StudentDao} used to perform CRUD operations on {@link Student} entities.
     */
    @Autowired
    public StudentService(StudentDao studentDao){
        this.studentDao = studentDao;
    }

    /**
     * Constructs a StudentService with the specified {@link StudentDao}.
     *
     * @param studentDao The {@link StudentDao} used to perform CRUD operations on {@link Student} entities.
     */
    public List<Student> studentList() {

        return studentDao.findAll();
    }

    /**
     * Retrieves a {@link Student} entity by its ID.
     *
     * @param id The ID of the {@link Student} to retrieve.
     * @return A {@link ResponseEntity} containing the {@link Student} if found, or {@link HttpStatus#NOT_FOUND} if not found.
     */
    public ResponseEntity<Student> getStudent(int id) {

        Optional<Student> studentOptional = studentDao.findById(id);

        if (studentOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(studentOptional.get(), HttpStatus.OK);

    }

    /**
     * Adds a new {@link Student} entity.
     *
     * @param newStudent The {@link Student} object to add.
     * @return A {@link ResponseEntity} containing the added {@link Student} if the ID is null, or {@link HttpStatus#BAD_REQUEST} if the ID is not null.
     */
    public ResponseEntity<Student> addStudent(Student newStudent) {

        if (newStudent.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        studentDao.save(newStudent);
        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
    }

    /**
     * Updates an existing {@link Student} entity.
     *
     * @param student The {@link Student} object containing the updated details.
     * @param id The ID of the {@link Student} to update.
     * @return A {@link ResponseEntity} containing the updated {@link Student} if the entity exists, or {@link HttpStatus#BAD_REQUEST} if the entity does not exist.
     */
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

    /**
     * Deletes a {@link Student} entity by its ID.
     *
     * @param id The ID of the {@link Student} to delete.
     * @return A {@link ResponseEntity} with {@link HttpStatus#OK} if the entity was deleted, or {@link HttpStatus#NOT_FOUND} if the entity does not exist.
     */
    public ResponseEntity<Student> deleteStudent (int id) {

        Optional<Student> studentOptional = studentDao.findById(id);

        if (studentOptional.isPresent() && studentOptional.isPresent()) {

            studentDao.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

