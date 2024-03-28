package com.project.AdminMNS.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class DocType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctype_id")
    protected Integer id;

    @Column(name = "doctype_name", length = 100)
    @Size(max = 100, message = "Nom DocType maximum 100 caractères")
    @NotBlank(message = "Le nom du doctype ne peut etre vide")
    protected String name;

    @Size(max = 255, message = "Description DocType maximum 255 caractères")
    @Column(name = "doctype_description", columnDefinition = "TEXT")
    protected String description;

    @OneToMany(mappedBy = "docType")
    protected List<Document> documentList;
}
