package com.project.AdminMNS.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AbsenceCause {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "absence_cause_id")
    protected Integer id;

    @Column(name = "absence_cause_name")
    protected String name;
}
