package com.project.adminmns.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.adminmns.dao.ModelUserDao;
import com.project.adminmns.model.ModelUser;
import com.project.adminmns.security.AdminPermission;
import com.project.adminmns.view.ModelUserView;
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
public class ModelUserController {

    ModelUserDao modelUserDao;

    @GetMapping("/user-by-email/{email}")
    @AdminPermission
    @JsonView(ModelUserView.class)
    public ResponseEntity<ModelUser> getByEmail(@PathVariable String email) {

        Optional<ModelUser> modelUserOptional = modelUserDao.findByEmail(email);

        if (modelUserOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(modelUserOptional.get(), HttpStatus.OK);

    }

    @GetMapping("/users/{id}")
    @AdminPermission
    @JsonView(ModelUserView.class)
    public ResponseEntity<ModelUser> get(@PathVariable int id) {

        Optional<ModelUser> modelUserOptional = modelUserDao.findById(id);

        if (modelUserOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(modelUserOptional.get(), HttpStatus.OK);

    }

    @GetMapping("/users/list")
    @JsonView(ModelUserView.class)
    @AdminPermission
    public List<ModelUser> list() {

        return modelUserDao.findAll();
    }

    @PostMapping("/users")
    @AdminPermission
    @JsonView(ModelUserView.class)
    public ResponseEntity<ModelUser> add(@Valid @RequestBody ModelUser newUser) {

        if (newUser.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        modelUserDao.save(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    @AdminPermission
    @JsonView(ModelUserView.class)
    public ResponseEntity<ModelUser> modified(@Valid @RequestBody ModelUser user, @PathVariable int id) {
        user.setId(id);

        Optional<ModelUser> userOptional = modelUserDao.findById(user.getId());

        if (userOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        modelUserDao.save(user);
        return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    @AdminPermission
    @JsonView(ModelUserView.class)
    public ResponseEntity<ModelUser> delete(@PathVariable int id) {

        Optional<ModelUser> userOptional = modelUserDao.findById(id);

        if (userOptional.isPresent()) {
            modelUserDao.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
