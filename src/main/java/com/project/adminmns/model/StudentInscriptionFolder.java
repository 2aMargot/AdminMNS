package com.project.adminmns.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "student_inscription_folder")
public class StudentInscriptionFolder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer incriptionFolderId;

    @Column(name = "inscription_folder_creation_date")
    @NotBlank(message = "La date de creation du dossier d'inscription étudiant ne peut etre vide")
    protected LocalDate creationDate;

    @Column(name = "inscription_folder_deadline")
    protected LocalDate deadline;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id")
    protected Student student;

    @ManyToOne(optional = false)
    @JoinColumn(name = "training_id")
    protected Training training;

    @OneToMany(mappedBy = "studentInscriptionFolder")
    @OnDelete(action = OnDeleteAction.CASCADE)
    protected List<Document> documentList;
}
