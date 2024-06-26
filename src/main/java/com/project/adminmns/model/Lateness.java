package com.project.adminmns.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.adminmns.view.StudentView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Lateness {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lateness_id")
    @JsonView(StudentView.class)
    protected Integer id;

    @Column(name = "lateness_creation_date")
    @NotBlank(message = "La date de création du retard ne peut etre vide")
    @JsonView(StudentView.class)
    protected LocalDate creationDate;

    @Column(name = "lateness_date")
    @NotBlank(message = "La date du retard ne peut etre vide")
    @JsonView(StudentView.class)
    protected LocalDate date;

    @Column(name = "lateness_justification")
    @JsonView(StudentView.class)
    protected String justification;

    @ManyToOne
    @JoinColumn(name = "lateness_cause_id")
    protected LatenessCause latenessCause;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id")
    protected Student studentLateness;
}
