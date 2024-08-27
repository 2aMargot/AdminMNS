package com.project.adminmns.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.adminmns.model.Absence;
import com.project.adminmns.model.AbsenceCause;
import com.project.adminmns.security.AdminPermission;
import com.project.adminmns.security.StudentPermission;
import com.project.adminmns.security.ValidatorPermission;
import com.project.adminmns.service.AbsenceService;
import com.project.adminmns.view.AbsenceView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("absence")
@RestController
@CrossOrigin
public class AbsenceController {

    private final AbsenceService absenceService;

    /**
     * Constructs an AbsenceController with the specified AbsenceService.
     *
     * @param absenceService The AbsenceService object used to handle absence-related operations.
     */
    @Autowired
    public AbsenceController(AbsenceService absenceService) {
        this.absenceService = absenceService;
    }


    /**
     * Retrieves a list of all absences.
     *
     * @return A list of Absence objects.
     */
    @GetMapping("/list")
    @JsonView(AbsenceView.class)
    @StudentPermission
    public List<Absence> list() {
        return absenceService.absenceList();
    }

    @GetMapping("/causes")
    @JsonView(AbsenceView.class)
    @StudentPermission
    public List<AbsenceCause> listAbsenceCause() {
        return absenceService.absenceCauseList();
    }

    @GetMapping("/student/{id}")
    @JsonView(AbsenceView.class)
    @StudentPermission
    public ResponseEntity<Absence[]> listAbsenceByStudent(@PathVariable int id) {
        return absenceService.getAbsenceByStudent(id);
    }

    /**
     * Retrieves an absence by its ID.
     *
     * @param id The ID of the absence to retrieve.
     * @return A ResponseEntity containing the Absence object if found, or an appropriate HTTP status.
     */
    @GetMapping("/{id}")
    @JsonView(AbsenceView.class)
    @ValidatorPermission
    public ResponseEntity<Absence> get(@PathVariable int id) {
        return absenceService.getAbsence(id);
    }

    /**
     * Adds a new absence.
     *
     * @param newAbsence The Absence object containing the details of the new absence to add.
     * @return A ResponseEntity containing the added Absence object, or an appropriate HTTP status.
     */
    @PostMapping
    @JsonView(AbsenceView.class)
    @StudentPermission
    public ResponseEntity<Absence> add(@Valid @RequestBody Absence newAbsence) {
        return absenceService.addAbsence(newAbsence);
    }

    /**
     * Updates an existing absence.
     *
     * @param absence The Absence object containing the updated absence details.
     * @param id The ID of the absence to update.
     * @return A ResponseEntity containing the updated Absence object, or an appropriate HTTP status.
     */
    @PutMapping("/{id}")
    @JsonView(AbsenceView.class)
    @AdminPermission
    public ResponseEntity<Absence> update(@Valid @RequestBody Absence absence, @PathVariable int id) {
        return absenceService.updateAbsence(absence, id);
    }

    /**
     * Deletes an existing absence by its ID.
     *
     * @param id The ID of the absence to delete.
     * @return A ResponseEntity containing the deleted Absence object if successful, or an appropriate HTTP status.
     */
    @DeleteMapping("/{id}")
    @JsonView(AbsenceView.class)
    @AdminPermission
    public ResponseEntity<Absence> delete(@PathVariable int id) {
        return absenceService.deleteAbsence(id);
    }


}