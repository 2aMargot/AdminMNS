package com.project.AdminMNS.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class LatenessCause {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lateness_cause_id")
    protected Integer id;

    @Column(name = "lateness_cause_name")
    protected String name;
}
