package com.project.adminmns.service;

import com.project.adminmns.dao.ModelUserDao;
import com.project.adminmns.dao.StudentDao;
import com.project.adminmns.model.ModelUser;
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


@Service
public class ModelUserService {

    ModelUserDao modelUserDao;
    StudentDao studentDao;


    public List<ModelUser> UserList() {

        return modelUserDao.findAll();
    }

    public ResponseEntity<ModelUser> getUserByEmail(String email) {

        Optional<ModelUser> modelUserOptional = modelUserDao.findByEmail(email);

        if (modelUserOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(modelUserOptional.get(), HttpStatus.OK);

    }

    public ResponseEntity<ModelUser> getUser(int id) {

        Optional<ModelUser> modelUserOptional = modelUserDao.findById(id);

        if (modelUserOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(modelUserOptional.get(), HttpStatus.OK);
    }

    public ResponseEntity<ModelUser> addUser(ModelUser newUser) {
        if (newUser.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        modelUserDao.save(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    public ResponseEntity<ModelUser> updateUser(ModelUser user, int id) {

        Optional<ModelUser> userOptional = modelUserDao.findById(user.getId());

        if (userOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        user.setId(id);
        this.modelUserDao.save(user);
        return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
    }

    public ResponseEntity<ModelUser> deleteUser(int id) {

        Optional<ModelUser> userOptional = modelUserDao.findById(id);
        Optional<Student> studentOptional = studentDao.findById(id);

        if (userOptional.isPresent() && studentOptional.isPresent()) {

            studentDao.deleteById(id);
            modelUserDao.deleteById(id);

        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}