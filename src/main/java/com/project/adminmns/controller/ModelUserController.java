package com.project.adminmns.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.adminmns.dao.*;
import com.project.adminmns.model.ModelUser;
import com.project.adminmns.model.Student;
import com.project.adminmns.security.AdminPermission;
import com.project.adminmns.security.StudentPermission;
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

    /**
     * Retrieves a user by their email address.
     *
     * @param email The email address of the user to retrieve.
     * @return A ResponseEntity containing the ModelUser object if found, or an appropriate HTTP status.
     */
    @GetMapping("/user-by-email/{email}")
    @StudentPermission
    @JsonView(ModelUserView.class)
    public ResponseEntity<ModelUser> getByEmail(@PathVariable String email) {

        return modelUserService.getUserByEmail(email);
    }

    /**
     * Retrieves a list of all users.
     *
     * @return A list of ModelUser objects.
     */
    @GetMapping("/list")
    @JsonView(ModelUserView.class)
    @AdminPermission
    public List<ModelUser> list() {

        return modelUserService.UserList();
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id The ID of the user to retrieve.
     * @return A ResponseEntity containing the ModelUser object if found, or an appropriate HTTP status.
     */
    @GetMapping("/{id}")
    @AdminPermission
    @JsonView(ModelUserView.class)
    public ResponseEntity<ModelUser> get(@PathVariable int id) {

        return modelUserService.getUser(id);
    }

    /**
     * Adds a new user.
     *
     * @param newUser The ModelUser object containing the details of the new user to add.
     * @return A ResponseEntity containing the added ModelUser object, or an appropriate HTTP status.
     */
    @PostMapping
    @AdminPermission
    @JsonView(ModelUserView.class)
    public ResponseEntity<ModelUser> add(@Valid @RequestBody ModelUser newUser) {

        return modelUserService.addUser(newUser);
    }

    /**
     * Updates an existing user.
     *
     * @param user The ModelUser object containing the updated user details.
     * @param id The ID of the user to update.
     * @return A ResponseEntity containing the updated ModelUser object, or an appropriate HTTP status.
     */
    @PutMapping("/{id}")
    @AdminPermission
    @JsonView(ModelUserView.class)
    public ResponseEntity<ModelUser> update(@Valid @RequestBody ModelUser user, @PathVariable int id) {

        return modelUserService.updateUser(user, id);
    }

    /**
     * Deletes an existing user by their ID.
     *
     * @param id The ID of the user to delete.
     * @return A ResponseEntity containing the deleted ModelUser object if successful, or an appropriate HTTP status.
     */
    @DeleteMapping("/{id}")
    @AdminPermission
    @JsonView(ModelUserView.class)
    public ResponseEntity<ModelUser> delete(@PathVariable int id) {

        return modelUserService.deleteUser(id);
    }
}