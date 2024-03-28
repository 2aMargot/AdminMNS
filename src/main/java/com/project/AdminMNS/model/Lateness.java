package com.project.AdminMNS.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Lateness {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lateness_id")
    protected Integer id;

    @Column(name = "lateness_creation_date")
    protected LocalDate creationDate;

    @Column(name = "lateness_date")
    protected LocalDate date;

    @Column(name = "lateness_justification")
    protected String justification;

    @ManyToOne
    @JoinColumn (name = "lateness_cause_id")
    protected LatenessCause latenessCause;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id")
    protected Student studentLateness;
}
