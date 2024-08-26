package com.project.adminmns.service;

import com.project.adminmns.dao.ModelUserDao;
import com.project.adminmns.model.ModelUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing {@link ModelUser} entities.
 * <p>
 * This service provides methods for performing CRUD operations on {@link ModelUser} entities.
 * It uses {@link ModelUserDao} for data access operations.
 * </p>
 */
@Service
public class ModelUserService {

    private final ModelUserDao modelUserDao;

    /**
     * Constructs a ModelUserService with the specified {@link ModelUserDao}.
     *
     * @param modelUserDao The {@link ModelUserDao} used to perform CRUD operations on {@link ModelUser} entities.
     */
    @Autowired
    public ModelUserService(ModelUserDao modelUserDao){
        this.modelUserDao = modelUserDao;
    }


    /**
     * Retrieves a list of all {@link ModelUser} entities.
     *
     * @return A list of {@link ModelUser} objects.
     */
    public List<ModelUser> UserList() {

        return modelUserDao.findAll();
    }

    /**
     * Retrieves a {@link ModelUser} entity by its email.
     *
     * @param email The email of the {@link ModelUser} to retrieve.
     * @return A {@link ResponseEntity} containing the {@link ModelUser} if found, or {@link HttpStatus#NOT_FOUND} if not found.
     */
    public ResponseEntity<ModelUser> getUserByEmail(String email) {

        Optional<ModelUser> modelUserOptional = modelUserDao.findByEmail(email);

        if (modelUserOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(modelUserOptional.get(), HttpStatus.OK);

    }

    /**
     * Retrieves a {@link ModelUser} entity by its ID.
     *
     * @param id The ID of the {@link ModelUser} to retrieve.
     * @return A {@link ResponseEntity} containing the {@link ModelUser} if found, or {@link HttpStatus#NOT_FOUND} if not found.
     */
    public ResponseEntity<ModelUser> getUser(int id) {

        Optional<ModelUser> modelUserOptional = modelUserDao.findById(id);

        if (modelUserOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(modelUserOptional.get(), HttpStatus.OK);
    }

    /**
     * Adds a new {@link ModelUser} entity.
     *
     * @param newUser The {@link ModelUser} object to add.
     * @return A {@link ResponseEntity} containing the added {@link ModelUser} if the ID is null, or {@link HttpStatus#BAD_REQUEST} if the ID is not null.
     */
    public ResponseEntity<ModelUser> addUser(ModelUser newUser) {
        if (newUser.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        modelUserDao.save(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    /**
     * Updates an existing {@link ModelUser} entity.
     *
     * @param user The {@link ModelUser} object containing the updated details.
     * @param id The ID of the {@link ModelUser} to update.
     * @return A {@link ResponseEntity} containing the updated {@link ModelUser} if the entity exists, or {@link HttpStatus#BAD_REQUEST} if the entity does not exist.
     */
    public ResponseEntity<ModelUser> updateUser(ModelUser user, int id) {

        Optional<ModelUser> userOptional = modelUserDao.findById(user.getId());

        if (userOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        user.setId(id);
        this.modelUserDao.save(user);
        return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
    }

    /**
     * Deletes a {@link ModelUser} entity by its ID.
     *
     * @param id The ID of the {@link ModelUser} to delete.
     * @return A {@link ResponseEntity} with {@link HttpStatus#OK}, or {@link HttpStatus#NOT_FOUND} if the entity does not exist.
     */
    public ResponseEntity<ModelUser> deleteUser(int id) {

        Optional<ModelUser> userOptional = modelUserDao.findById(id);

        if (userOptional.isPresent()) {

            modelUserDao.deleteById(id);

        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}