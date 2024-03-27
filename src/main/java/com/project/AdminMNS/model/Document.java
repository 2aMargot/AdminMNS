package com.project.AdminMNS.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    protected Integer id;

    @Column(name = "document_name")
    protected String name;

    @Column(name = "document_link")
    protected String link;

    @Column(name = "document_validity")
    protected Boolean validity;

    @Column(name = "document_deposite_date")
    protected LocalDate depositeDate;

    @Column(name = "document_validation_date")
    protected LocalDate validationDate;

    @Column(name = "document_refusal_date")
    protected LocalDate refusalDate;
}
