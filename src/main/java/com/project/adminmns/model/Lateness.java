package com.project.adminmns.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.adminmns.view.LatenessView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

/**
 * Represents a lateness record in the system.
 * <p>
 * The {@link Lateness} class captures the details of a lateness event for a student, including the date of the event,
 * the justification, and its validity. It also associates the lateness with a specific cause and a student.
 * This class is mapped to a database table where lateness records are stored.
 * </p>
 */
@Entity
@Data
public class Lateness {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lateness_id")
    protected Integer id;

    @Column(name = "lateness_creation_date")
    @NotBlank(message = "La date de création du retard ne peut etre vide")
    @JsonView(LatenessView.class)
    protected LocalDate creationDate;

    @Column(name = "lateness_date")
    @NotBlank(message = "La date du retard ne peut etre vide")
    @JsonView(LatenessView.class)
    protected LocalDate date;

    @Column(name = "lateness_justification")
    @JsonView(LatenessView.class)
    protected String justification;

    @Column(name = "lateness_validity")
    @JsonView(LatenessView.class)
    protected Boolean validity;

    @ManyToOne
    @JoinColumn(name = "lateness_cause_id")
    protected LatenessCause latenessCause;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id")
    @JsonView(LatenessView.class)
    protected Student studentLateness;
}
