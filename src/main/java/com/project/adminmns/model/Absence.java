package com.project.adminmns.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.adminmns.view.AbsenceView;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Absence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "absence_id")
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
    protected AbsenceCause absenceCause;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id")
    protected Student studentAbsence;
}
