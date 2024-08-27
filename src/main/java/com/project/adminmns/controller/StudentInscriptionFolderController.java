package com.project.adminmns.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.adminmns.model.StudentInscriptionFolder;
import com.project.adminmns.security.AdminPermission;
import com.project.adminmns.security.StudentPermission;
import com.project.adminmns.service.StudentInscriptionFolderService;
import com.project.adminmns.view.StudentInscriptionFolderView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/student-inscription-folder")
@RestController
@CrossOrigin
public class StudentInscriptionFolderController {

    private final StudentInscriptionFolderService studentInscriptionFolderService;

    /**
     * Constructs a StudentInscriptionFolderController with the specified StudentInscriptionFolderService.
     *
     * @param studentInscriptionFolderService The StudentInscriptionFolderService object used to handle student inscription folder-related operations.
     */
    @Autowired
    public StudentInscriptionFolderController(StudentInscriptionFolderService studentInscriptionFolderService){
        this.studentInscriptionFolderService = studentInscriptionFolderService;
    }

    /**
     * Retrieves a list of all student inscription folders.
     *
     * @return A list of StudentInscriptionFolder objects.
     */
    @GetMapping("/list")
    @JsonView(StudentInscriptionFolderView.class)
    @StudentPermission
    public List<StudentInscriptionFolder> list() {

        return studentInscriptionFolderService.folderList();
    }


    /**
     * Retrieves a student inscription folder by ID.
     *
     * @param id The ID of the student inscription folder to retrieve.
     * @return A ResponseEntity containing the StudentInscriptionFolder object if found, or an appropriate HTTP status.
     */
    @GetMapping("/{id}")
    @AdminPermission
    @JsonView(StudentInscriptionFolderView.class)
    public ResponseEntity<StudentInscriptionFolder> get(@PathVariable int id) {

        return studentInscriptionFolderService.getFolder(id);
    }


    /**
     * Adds a new student inscription folder.
     *
     * @param newStudentInscriptionFolder The StudentInscriptionFolder object containing the details of the new folder to add.
     * @return A ResponseEntity containing the added StudentInscriptionFolder object, or an appropriate HTTP status.
     */
    @PostMapping
    @AdminPermission
    @JsonView(StudentInscriptionFolderView.class)
    public ResponseEntity<StudentInscriptionFolder> add(@Valid @RequestBody StudentInscriptionFolder newStudentInscriptionFolder) {

        return studentInscriptionFolderService.addFolder(newStudentInscriptionFolder);
    }

    /**
     * Updates an existing student inscription folder.
     *
     * @param studentInscriptionFolder The StudentInscriptionFolder object containing the updated folder details.
     * @param id The ID of the folder to update.
     * @return A ResponseEntity containing the updated StudentInscriptionFolder object, or an appropriate HTTP status.
     */
    @PutMapping("/{id}")
    @AdminPermission
    @JsonView(StudentInscriptionFolderView.class)
    public ResponseEntity<StudentInscriptionFolder> update(@Valid @RequestBody StudentInscriptionFolder studentInscriptionFolder, @PathVariable int id) {

        return studentInscriptionFolderService.updateFolder(studentInscriptionFolder, id);
    }

    /**
     * Deletes an existing student inscription folder by its ID.
     *
     * @param id The ID of the student inscription folder to delete.
     * @return A ResponseEntity containing the deleted StudentInscriptionFolder object if successful, or an appropriate HTTP status.
     */
    @DeleteMapping("/{id}")
    @AdminPermission
    @JsonView(StudentInscriptionFolderView.class)
    public ResponseEntity<StudentInscriptionFolder> delete(@PathVariable int id) {

        return studentInscriptionFolderService.deleteFolder(id);
    }
}