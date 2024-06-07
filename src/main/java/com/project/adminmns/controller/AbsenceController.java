package com.project.adminmns.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.adminmns.model.Absence;
import com.project.adminmns.security.AdminPermission;
import com.project.adminmns.security.StudentPermission;
import com.project.adminmns.service.AbsenceService;
import com.project.adminmns.view.AbsenceView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RequestMapping("absence")
@RestController
@CrossOrigin //à configurer sur une application/API déployée
@AllArgsConstructor
public class AbsenceController {

    private final AbsenceService absenceService;

    @GetMapping("/list")
    @JsonView(AbsenceView.class)
    @AdminPermission
    public List<Absence> list() {
        return absenceService.AbsenceList();
    }

    @GetMapping("/{id}")
    @JsonView(AbsenceView.class)
    @AdminPermission
    public ResponseEntity<Absence> get(@PathVariable int id) {
        return absenceService.getAbsence(id);
    }

    @PostMapping
    @JsonView(AbsenceView.class)
    @AdminPermission
    public ResponseEntity<Absence> add(@Valid @RequestBody Absence newAbsence) {
        return absenceService.addAbsence(newAbsence);
    }

    @PutMapping("/{id}")
    @JsonView(AbsenceView.class)
    @AdminPermission
    public ResponseEntity<Absence> update(@Valid @RequestBody Absence absence, @PathVariable int id) {
        return absenceService.updateAbsence(absence, id);
    }

    @DeleteMapping("/{id}")
    @JsonView(AbsenceView.class)
    @AdminPermission
    public ResponseEntity<Absence> delete(@PathVariable int id) {
        return absenceService.deleteAbsence(id);
    }

    @ResponseBody
    @StudentPermission
    @PostMapping("/upload")
    public ResponseEntity<byte[]> uploadFile(InputStream inputStream, String fileName) throws IOException {
        return absenceService.uploadAbsence(inputStream, fileName);
    }

    @ResponseBody
    @GetMapping("/download/{fileName}")
    public byte[] getFile(@PathVariable String fileName) throws IOException {
        return absenceService.getFileFromUploadFolder(fileName);
    }
}