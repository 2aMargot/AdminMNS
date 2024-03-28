package com.project.AdminMNS.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class StudentInscriptionFolder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer incriptionFolderId;

    @Column(name = "inscription_folder_creation_date")
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
    protected List<Document> documentList;
}
