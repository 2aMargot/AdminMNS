package com.project.adminmns.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.adminmns.view.AbsenceView;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

/**
 * Represents an absence record for a student.
 * <p>
 * The {@link Absence} class captures details about a student's absence, including its creation date, start and end dates,
 * justification, and validity. It also associates the absence with a specific cause and the student who was absent.
 * </p>
 */
@Entity
@Data
public class Absence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "absence_id")
    @JsonView(AbsenceView.class)
    protected Integer id;

    @Column(name = "absence_creation_date")
    @JsonView(AbsenceView.class)
    protected LocalDate creationDate;

    @Column(name = "absence_start")
    @JsonView(AbsenceView.class)
    protected LocalDate start;

    @Column(name = "absence_end")
    @JsonView(AbsenceView.class)
    protected LocalDate end;

    @Column(name = "absence_justification")
    @JsonView(AbsenceView.class)
    protected String justification;

    @Column(name = "absence_validity")
    @JsonView(AbsenceView.class)
    protected Boolean validity;

    @ManyToOne
    @JoinColumn(name = "absence_cause_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonView(AbsenceView.class)
    protected AbsenceCause absenceCause;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id")
    @JsonView(AbsenceView.class)
    @OnDelete(action = OnDeleteAction.CASCADE)
    protected Student studentAbsence;
}
