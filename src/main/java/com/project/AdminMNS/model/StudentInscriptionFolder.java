package com.project.AdminMNS.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

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
}
