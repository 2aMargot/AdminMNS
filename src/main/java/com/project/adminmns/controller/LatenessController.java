package com.project.adminmns.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.adminmns.dao.LatenessDao;
import com.project.adminmns.model.Lateness;
import com.project.adminmns.security.AdminPermission;
import com.project.adminmns.security.StudentPermission;
import com.project.adminmns.service.LatenessService;
import com.project.adminmns.view.LatenessView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/lateness")
@RestController
@CrossOrigin
public class LatenessController {

    private final LatenessService latenessService;

    /**
     * Constructs a LatenessController with the specified LatenessService.
     *
     * @param latenessService The LatenessService object used to handle lateness-related operations.
     */
    @Autowired
    public LatenessController(LatenessService latenessService){
        this.latenessService = latenessService;
    }

    /**
     * Retrieves a list of all lateness records.
     *
     * @return A list of Lateness objects.
     */
    @GetMapping("/list")
    @JsonView(LatenessView.class)
    @StudentPermission
    public List<Lateness> list() {

        return latenessService.latenessList();
    }


    /**
     * Retrieves a lateness record by its ID.
     *
     * @param id The ID of the lateness record to retrieve.
     * @return A ResponseEntity containing the Lateness object if found, or an appropriate HTTP status.
     */
    @GetMapping("/{id}")
    @JsonView(LatenessView.class)
    @AdminPermission
    public ResponseEntity<Lateness> get(@PathVariable int id) {

        return latenessService.getLateness(id);
    }

    /**
     * Adds a new lateness record.
     *
     * @param newLateness The Lateness object containing the details of the new lateness record to add.
     * @return A ResponseEntity containing the added Lateness object, or an appropriate HTTP status.
     */
    @PostMapping
    @JsonView(LatenessView.class)
    @AdminPermission
    public ResponseEntity<Lateness> add(@Valid @RequestBody Lateness newLateness) {

        return latenessService.addLateness(newLateness);
    }

    /**
     * Updates an existing lateness record.
     *
     * @param lateness The Lateness object containing the updated lateness record details.
     * @param id The ID of the lateness record to update.
     * @return A ResponseEntity containing the updated Lateness object, or an appropriate HTTP status.
     */
    @PutMapping("/{id}")
    @JsonView(LatenessView.class)
    @AdminPermission
    public ResponseEntity<Lateness> update(@Valid @RequestBody Lateness lateness, @PathVariable int id) {

        return latenessService.updateLateness(lateness, id);
    }

    /**
     * Deletes an existing lateness record by its ID.
     *
     * @param id The ID of the lateness record to delete.
     * @return A ResponseEntity containing the deleted Lateness object if successful, or an appropriate HTTP status.
     */
    @DeleteMapping("/{id}")
    @JsonView(LatenessView.class)
    @AdminPermission
    public ResponseEntity<Lateness> delete(@PathVariable int id) {

        return latenessService.deleteLateness(id);
    }
}

