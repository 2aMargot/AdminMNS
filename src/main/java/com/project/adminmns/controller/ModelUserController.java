package com.project.adminmns.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.adminmns.dao.*;
import com.project.adminmns.model.ModelUser;
import com.project.adminmns.model.Student;
import com.project.adminmns.security.AdminPermission;
import com.project.adminmns.service.ModelUserService;
import com.project.adminmns.view.ModelUserView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/users")
@RestController
@CrossOrigin
public class ModelUserController {

    private final ModelUserService modelUserService;

    @Autowired
    public ModelUserController(ModelUserService modelUserService){
        this.modelUserService = modelUserService;
    }

    @GetMapping("/user-by-email/{email}")
    @AdminPermission
    @JsonView(ModelUserView.class)
    public ResponseEntity<ModelUser> getByEmail(@PathVariable String email) {

        return modelUserService.getUserByEmail(email);
    }

    @GetMapping("/list")
    @JsonView(ModelUserView.class)
    @AdminPermission
    public List<ModelUser> list() {

        return modelUserService.UserList();
    }

    @GetMapping("/{id}")
    @AdminPermission
    @JsonView(ModelUserView.class)
    public ResponseEntity<ModelUser> get(@PathVariable int id) {

        return modelUserService.getUser(id);
    }

    @PostMapping
    @AdminPermission
    @JsonView(ModelUserView.class)
    public ResponseEntity<ModelUser> add(@Valid @RequestBody ModelUser newUser) {

        return modelUserService.addUser(newUser);
    }

    @PutMapping("/{id}")
    @AdminPermission
    @JsonView(ModelUserView.class)
    public ResponseEntity<ModelUser> update(@Valid @RequestBody ModelUser user, @PathVariable int id) {

        return modelUserService.updateUser(user, id);
    }

    @DeleteMapping("/{id}")
    @AdminPermission
    @JsonView(ModelUserView.class)
    public ResponseEntity<ModelUser> delete(@PathVariable int id) {

        return modelUserService.deleteUser(id);
    }
}