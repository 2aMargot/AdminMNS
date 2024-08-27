package com.project.adminmns.service;

import com.project.adminmns.dao.AbsenceCauseDao;
import com.project.adminmns.dao.AbsenceDao;
import com.project.adminmns.dao.StudentDao;
import com.project.adminmns.model.Absence;
import com.project.adminmns.model.AbsenceCause;
import com.project.adminmns.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing {@link Absence} entities and file uploads.
 * <p>
 * This service provides methods for performing CRUD operations on {@link Absence} entities,
 * as well as handling file uploads related to absences.
 * It uses {@link AbsenceDao} for data access operations and {@link FileUploadService} for file management.
 * </p>
 */
@Service
public class AbsenceService {

    private final AbsenceDao absenceDao;
    private final StudentDao studentDao;
    private final AbsenceCauseDao absenceCauseDao;
    private final FileUploadService fileUploadService;
    private final StudentService studentService;

    /**
     * Constructs an AbsenceService with the specified {@link AbsenceDao} and {@link FileUploadService}.
     *
     * @param absenceDao The {@link AbsenceDao} used to perform CRUD operations on {@link Absence} entities.
     * @param fileUploadService The {@link FileUploadService} used for file upload and retrieval.
     */
    @Autowired
    public AbsenceService(AbsenceDao absenceDao, FileUploadService fileUploadService, StudentService studentService, AbsenceCauseDao absenceCauseDao, StudentDao studentDao){
        this.absenceDao = absenceDao;
        this.absenceCauseDao = absenceCauseDao;
        this.fileUploadService = fileUploadService;
        this.studentService = studentService;
        this.studentDao = studentDao;
    }


    /**
     * Retrieves a list of all {@link Absence} entities.
     *
     * @return A list of {@link Absence} objects.
     */
    public List<Absence> absenceList() {
        return absenceDao.findAll();
    }

    /**
     * Retrieves an {@link Absence} entity by its ID.
     *
     * @param id The ID of the {@link Absence} to retrieve.
     * @return A {@link ResponseEntity} containing the {@link Absence} if found, or {@link HttpStatus#NOT_FOUND} if not found.
     */
    public ResponseEntity<Absence> getAbsence(int id) {

        Optional<Absence> absenceOptional = this.absenceDao.findById(id);

        if (absenceOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(absenceOptional.get(), HttpStatus.OK);
    }

    public ResponseEntity<Absence[]> getAbsenceByStudent(int id) {
        Optional<Student> studentOptional = this.studentDao.findById(id);

        if (studentOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Absence[] absencelist = this.absenceDao.findByIdStudent(id);
        return new ResponseEntity<>(absencelist, HttpStatus.OK);
    }

    /**
     * Adds a new {@link Absence} entity.
     *
     * @param newAbsence The {@link Absence} object to add.
     * @return A {@link ResponseEntity} containing the added {@link Absence} if the ID is null,
     * or {@link HttpStatus#OK} if the absence ID is already present in the database.
     */
    public ResponseEntity<Absence> addAbsence(Absence newAbsence) {

        if (newAbsence.getId() != null) {
            Optional<Absence> absenceOptional = this.absenceDao.findById(newAbsence.getId());

            if (absenceOptional.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            this.absenceDao.save(newAbsence);

            return new ResponseEntity<>(absenceOptional.get(), HttpStatus.OK);
        }

        newAbsence.setStudentAbsence(studentService.getStudentByEmail(newAbsence.getStudentAbsence().getEmail()));
        absenceDao.save(newAbsence);

        return new ResponseEntity<>(newAbsence, HttpStatus.CREATED);
    }

    /**
     * Updates an existing {@link Absence} entity.
     *
     * @param absence The {@link Absence} object containing the updated details.
     * @param id The ID of the {@link Absence} to update.
     * @return A {@link ResponseEntity} containing the updated {@link Absence} if the entity exists,
     * or {@link HttpStatus#BAD_REQUEST} if the entity does not exist.
     */
    public ResponseEntity<Absence> updateAbsence(Absence absence, int id) {

        Optional<Absence> absenceOptional = absenceDao.findById(absence.getId());

        if (absenceOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        absence.setId(id);
        absence.setStudentAbsence(studentService.getStudentByEmail(absence.getStudentAbsence().getEmail()));
        this.absenceDao.save(absence);
        return new ResponseEntity<>(absenceOptional.get(), HttpStatus.OK);
    }

    /**
     * Deletes an {@link Absence} entity by its ID.
     *
     * @param id The ID of the {@link Absence} to delete.
     * @return A {@link ResponseEntity} with {@link HttpStatus#OK} if the entity was deleted,
     * or {@link HttpStatus#NOT_FOUND} if the entity does not exist.
     */
    public ResponseEntity<Absence> deleteAbsence(int id) {

        Optional<Absence> absenceOptional = this.absenceDao.findById(id);

        if (absenceOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        absenceDao.deleteById(id);
        return new ResponseEntity<>(absenceOptional.get(), HttpStatus.OK);
    }

    /**
     * Uploads a file related to an absence.
     *
     * @param inputStream The {@link InputStream} of the file to upload.
     * @param fileName The name of the file.
     * @return A {@link ResponseEntity} with {@link HttpStatus#CREATED} if the upload was successful.
     * @throws IOException If an I/O error occurs during file upload.
     */
    public ResponseEntity<byte[]> uploadAbsence(InputStream inputStream, String fileName) throws IOException {

        this.fileUploadService.uploadToLocalFileSystem(inputStream, fileName);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Retrieves a file related to an absence.
     *
     * @param fileName The name of the file to retrieve.
     * @return A {@link ResponseEntity} with {@link HttpStatus#ACCEPTED} if the file retrieval was successful.
     * @throws IOException If an I/O error occurs during file retrieval.
     */
    public ResponseEntity<Byte[]> getFile(String fileName) throws IOException {

        this.fileUploadService.getFileFromUploadFolder(fileName);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    public List<AbsenceCause> absenceCauseList() {
        return absenceCauseDao.findAll();
    }
}
