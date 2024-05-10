package com.project.adminmns.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.adminmns.view.StudentInscriptionFolderView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Data
@Table(name = "student_inscription_folder")
public class StudentInscriptionFolder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(name = "inscription_folder_creation_date")
    @NotBlank(message = "La date de creation du dossier d'inscription étudiant ne peut etre vide")
//    @JsonView(StudentInscriptionFolderView.class)
    protected LocalDate creationDate;

    @Column(name = "inscription_folder_deadline")
//    @JsonView(StudentInscriptionFolderView.class)
    protected LocalDate deadline;

    @Column(name = "validity")
    @JsonView(StudentInscriptionFolderView.class)
    protected Boolean validity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id")
//    @JsonView(StudentInscriptionFolderView.class)
    protected Student student;

    @ManyToOne(optional = false)
    @JoinColumn(name = "training_id")
    protected Training training;

    @OneToMany(mappedBy = "studentInscriptionFolder")
    @OnDelete(action = OnDeleteAction.CASCADE)
    protected List<Document> documentList;
}
