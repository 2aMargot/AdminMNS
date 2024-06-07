package com.project.adminmns.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.adminmns.dao.StudentInscriptionFolderDao;
import com.project.adminmns.model.StudentInscriptionFolder;
import com.project.adminmns.security.AdminPermission;
import com.project.adminmns.service.StudentInscriptionFolderService;
import com.project.adminmns.view.StudentInscriptionFolderView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/student-inscription-folder")
@RestController
@CrossOrigin
//@AllArgsConstructor
public class StudentInscriptionFolderController {

    private final StudentInscriptionFolderService studentInscriptionFolderService;

    @Autowired
    public StudentInscriptionFolderController(StudentInscriptionFolderService studentInscriptionFolderService){
        this.studentInscriptionFolderService = studentInscriptionFolderService;
    }

    @GetMapping("/list")
    @JsonView(StudentInscriptionFolderView.class)
    @AdminPermission
    public List<StudentInscriptionFolder> list() {

        return studentInscriptionFolderService.folderList();
    }


    @GetMapping("/{id}")
    @AdminPermission
    @JsonView(StudentInscriptionFolderView.class)
    public ResponseEntity<StudentInscriptionFolder> get(@PathVariable int id) {

        return studentInscriptionFolderService.getFolder(id);
    }


    @PostMapping
    @AdminPermission
    @JsonView(StudentInscriptionFolderView.class)
    public ResponseEntity<StudentInscriptionFolder> add(@Valid @RequestBody StudentInscriptionFolder newStudentInscriptionFolder) {

        return studentInscriptionFolderService.addFolder(newStudentInscriptionFolder);
    }

    @PutMapping("/{id}")
    @AdminPermission
    @JsonView(StudentInscriptionFolderView.class)
    public ResponseEntity<StudentInscriptionFolder> update(@Valid @RequestBody StudentInscriptionFolder studentInscriptionFolder, @PathVariable int id) {

        return studentInscriptionFolderService.updateFolder(studentInscriptionFolder, id);
    }

    @DeleteMapping("/{id}")
    @AdminPermission
    @JsonView(StudentInscriptionFolderView.class)
    public ResponseEntity<StudentInscriptionFolder> delete(@PathVariable int id) {

        return studentInscriptionFolderService.deleteFolder(id);
    }
}