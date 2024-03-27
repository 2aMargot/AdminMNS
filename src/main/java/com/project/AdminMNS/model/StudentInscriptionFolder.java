package com.project.AdminMNS.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class StudentInscriptionFolder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer incriptionFolderId;

    protected LocalDate inscriptionFolderCreationDate;

    protected LocalDate inscriptionFolderDeadline;
}
