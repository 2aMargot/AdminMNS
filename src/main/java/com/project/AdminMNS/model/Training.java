package com.project.AdminMNS.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Data
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "training_id")
    protected Integer id;

    @Column(name = "training_name")
    protected String name;

    @Column(name = "training_start")
    protected LocalDate start;

    @Column(name = "training_end")
    protected LocalDate end;

}
