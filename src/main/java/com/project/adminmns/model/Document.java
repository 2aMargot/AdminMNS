package com.project.adminmns.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    protected Integer id;

    @Column(name = "document_name", length = 50)
    @NotBlank(message = "Le nom du document ne peut etre vide")
    @Size(max = 50, message = "Le nom du document ne peut etre supérieur à maximum 50 caractères")
    protected String name;

    @Column(name = "document_link")
    @NotBlank(message = "Le lien du document ne peut etre vide")
    protected String link;

    @Column(name = "document_validity")
    protected Boolean validity;

    @Column(name = "document_deposite_date")
    @NotBlank(message = "La date de dépôt du document ne peut etre vide")
    protected LocalDate depositeDate;

    @Column(name = "document_validation_date")
    protected LocalDate validationDate;

    @Column(name = "document_refusal_date")
    protected LocalDate refusalDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_inscription_folder_id")
    protected StudentInscriptionFolder studentInscriptionFolder;

    @ManyToOne(optional = false)
    @JoinColumn(name = "doctype_id")
    protected DocType docType;
}
