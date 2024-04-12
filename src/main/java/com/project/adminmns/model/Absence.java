package com.project.adminmns.model;

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
    protected LocalDate creationDate;

    @Column(name = "absence_start")
    protected LocalDate start;

    @Column(name = "absence_end")
    protected LocalDate end;

    @Column(name = "absence_justification")
    protected String justification;

    @Column(name = "absence_validity")
    protected Boolean validity;

    @ManyToOne
    @JoinColumn(name = "absence_cause_id")
    protected AbsenceCause absenceCause;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id")
    protected Student studentAbsence;
}
