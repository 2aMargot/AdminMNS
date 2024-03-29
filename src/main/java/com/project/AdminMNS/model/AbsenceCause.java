package com.project.AdminMNS.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "absence_cause")
public class AbsenceCause {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "absence_cause_id")
    protected Integer id;

    @Column(name = "absence_cause_name", length = 50)
    @Size(max = 50, message = "Nom AbsenceCause maximum 50 caractères")
    @NotBlank(message = "Le nom de AbsenceCause ne peut etre vide")
    protected String name;
}
