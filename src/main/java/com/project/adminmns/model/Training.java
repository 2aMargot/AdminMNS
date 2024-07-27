package com.project.adminmns.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.adminmns.view.TrainingView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a training session in the system.
 * <p>
 * The {@link Training} class captures the details of a training session, including its name, start and end dates,
 * and the associations with student inscription folders and document types. This class is mapped to a database table
 * where training session details are stored.
 * </p>
 */
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
    @JsonView(TrainingView.class)
    protected String name;

    @Column(name = "training_start")
    @JsonView(TrainingView.class)
    protected LocalDate start;

    @Column(name = "training_end")
    @JsonView(TrainingView.class)
    protected LocalDate end;

    @OneToMany(mappedBy = "training")
    @JsonView(TrainingView.class)
    @OnDelete(action = OnDeleteAction.CASCADE)
    protected List<StudentInscriptionFolder> studentInscriptionFolderList;

    @ManyToMany
    @JoinTable(
            name = "doctypextraining",
            joinColumns = @JoinColumn(name = "training_id"),
            inverseJoinColumns = @JoinColumn(name = "doctype_id")
    )
    protected List<DocType> docTypeList = new ArrayList<>();
}
