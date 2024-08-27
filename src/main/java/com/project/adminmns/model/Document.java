package com.project.adminmns.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.adminmns.view.DocumentView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

/**
 * Represents a document associated with a student's inscription folder.
 * <p>
 * The Document class captures the details of a document, including its name, link, validity, and important dates
 * related to its deposition, validation, and refusal. It also associates the document with a specific student's inscription
 * folder and document type.
 * </p>
 */
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
    @JsonView(DocumentView.class)
    protected String name;

    @Column(name = "document_link")
    @NotBlank(message = "Le lien du document ne peut etre vide")
    @JsonView(DocumentView.class)
    protected String link;

    @Column(name = "document_validity")
    @JsonView(DocumentView.class)
    protected Boolean validity;

    @Column(name = "document_deposite_date")
    @NotNull(message = "La date de dépôt du document ne peut etre vide")
    @JsonView(DocumentView.class)
    protected LocalDate depositeDate;

    @Column(name = "document_validation_date")
    @NotNull(message = "La date de validation du document ne peut etre vide")
    @JsonView(DocumentView.class)
    protected LocalDate validationDate;

    @Column(name = "document_refusal_date")
    @NotNull(message = "La date de refus du document ne peut etre vide")
    @JsonView(DocumentView.class)
    protected LocalDate refusalDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_inscription_folder_id")
    protected StudentInscriptionFolder studentInscriptionFolder;

    @ManyToOne(optional = false)
    @JoinColumn(name = "doctype_id")
    protected DocType docType;

}