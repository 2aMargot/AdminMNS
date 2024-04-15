package com.project.adminmns.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "training_id")
    protected Integer id;

    @Column(name = "training_name", length = 100)
    @Size(max = 100, message = "Nom Training maximum 100 caractères")
    @NotBlank(message = "Le nom de la formation ne peut etre vide")
    protected String name;

    @Column(name = "training_start")
    protected LocalDate start;

    @Column(name = "training_end")
    protected LocalDate end;

    @OneToMany(mappedBy = "training", cascade = CascadeType.REMOVE)
    protected List<StudentInscriptionFolder> studentInscriptionFolderList;

    @ManyToMany
    @JoinTable(
            name = "doctypextraining",
            joinColumns = @JoinColumn (name = "training_id"),
            inverseJoinColumns = @JoinColumn(name = "doctype_id")
    )
    protected List<DocType> docTypeList = new ArrayList<>();

}
