package com.project.adminmns.service;

import com.project.adminmns.dao.StudentInscriptionFolderDao;
import com.project.adminmns.model.StudentInscriptionFolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing student inscription folders.
 * <p>
 * This service provides methods for retrieving, adding, updating, and deleting {@link StudentInscriptionFolder} entities.
 * </p>
 */
@Service
public class StudentInscriptionFolderService {

    private final StudentInscriptionFolderDao studentInscriptionFolderDao;

    /**
     * Constructs a {@link StudentInscriptionFolderService} with the specified {@link StudentInscriptionFolderDao}.
     *
     * @param studentInscriptionFolderDao The {@link StudentInscriptionFolderDao} used to perform CRUD operations on {@link StudentInscriptionFolder} entities.
     */
    @Autowired
    public StudentInscriptionFolderService(StudentInscriptionFolderDao studentInscriptionFolderDao){
        this.studentInscriptionFolderDao = studentInscriptionFolderDao;
    }

    /**
     * Retrieves a list of all {@link StudentInscriptionFolder} entities.
     *
     * @return A list of {@link StudentInscriptionFolder} objects.
     */
    public List<StudentInscriptionFolder> folderList() {
        return studentInscriptionFolderDao.findAll();
    }

    /**
     * Retrieves a {@link StudentInscriptionFolder} entity by its ID.
     *
     * @param id The ID of the {@link StudentInscriptionFolder} to retrieve.
     * @return A {@link ResponseEntity} containing the {@link StudentInscriptionFolder} if found, or {@link HttpStatus#NOT_FOUND} if not found.
     */
    public ResponseEntity<StudentInscriptionFolder> getFolder(int id) {

        Optional<StudentInscriptionFolder> studentInscriptionFolderOptional = studentInscriptionFolderDao.findById(id);

        if (studentInscriptionFolderOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(studentInscriptionFolderOptional.get(), HttpStatus.OK);
    }

    /**
     * Adds a new {@link StudentInscriptionFolder} entity.
     *
     * @param newStudentInscriptionFolder The {@link StudentInscriptionFolder} object to add.
     * @return A {@link ResponseEntity} containing the added {@link StudentInscriptionFolder} if the ID is null, or {@link HttpStatus#BAD_REQUEST} if the ID is not null.
     */
    public ResponseEntity<StudentInscriptionFolder> addFolder(StudentInscriptionFolder newStudentInscriptionFolder) {

        if (newStudentInscriptionFolder.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        studentInscriptionFolderDao.save(newStudentInscriptionFolder);
        return new ResponseEntity<>(newStudentInscriptionFolder, HttpStatus.CREATED);
    }

    /**
     * Updates an existing {@link StudentInscriptionFolder} entity.
     *
     * @param studentInscriptionFolder The {@link StudentInscriptionFolder} object containing the updated details.
     * @param id The ID of the {@link StudentInscriptionFolder} to update.
     * @return A {@link ResponseEntity} containing the updated {@link StudentInscriptionFolder} if the entity exists, or {@link HttpStatus#BAD_REQUEST} if the entity does not exist.
     */
    public ResponseEntity<StudentInscriptionFolder> updateFolder(StudentInscriptionFolder studentInscriptionFolder, int id) {

        Optional<StudentInscriptionFolder> studentInscriptionFolderOptional = studentInscriptionFolderDao.findById(studentInscriptionFolder.getId());

        if (studentInscriptionFolderOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        studentInscriptionFolder.setId(id);
        studentInscriptionFolderDao.save(studentInscriptionFolder);
        return new ResponseEntity<>(studentInscriptionFolderOptional.get(), HttpStatus.OK);
    }

    /**
     * Deletes a {@link StudentInscriptionFolder} entity by its ID.
     *
     * @param id The ID of the {@link StudentInscriptionFolder} to delete.
     * @return A {@link ResponseEntity} with {@link HttpStatus#OK} if the deletion was successful, or {@link HttpStatus#NOT_FOUND} if the entity does not exist.
     */
    public ResponseEntity<StudentInscriptionFolder> deleteFolder(int id) {

        Optional<StudentInscriptionFolder> studentInscriptionFolderOptional = studentInscriptionFolderDao.findById(id);

        if (studentInscriptionFolderOptional.isPresent()) {
            studentInscriptionFolderDao.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
