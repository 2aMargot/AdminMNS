package com.project.adminmns.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.adminmns.view.AbsenceView;
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
    @JsonView(AbsenceView.class)
    protected Integer id;

    @Column(name = "absence_cause_name", length = 50)
    @Size(max = 50, message = "Nom AbsenceCause maximum 50 caractères")
    @NotBlank(message = "Le nom de AbsenceCause ne peut etre vide")
    @JsonView(AbsenceView.class)
    protected String name;
}
