package com.project.AdminMNS.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "lateness_cause")
public class LatenessCause {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lateness_cause_id")
    protected Integer id;

    @Column(name = "lateness_cause_name", length = 50)
    @Size(max = 50, message = "Nom LatenessCause maximum 50 caractères")
    @NotBlank(message = "Le nom de LatenessCause ne peut etre vide")
    protected String name;
}
