package com.project.adminmns.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.adminmns.view.DocTypeView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "doc_type")
public class DocType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctype_id")
    protected Integer id;

    @Column(name = "doctype_name", length = 100)
    @Size(max = 100, message = "Nom DocType maximum 100 caractères")
    @NotBlank(message = "Le nom du doctype ne peut etre vide")
    @JsonView(DocTypeView.class)
    protected String name;

    @Size(max = 255, message = "Description DocType maximum 255 caractères")
    @Column(name = "doctype_description", columnDefinition = "TEXT")
    @JsonView(DocTypeView.class)
    protected String description;

    @OneToMany(mappedBy = "docType", cascade = CascadeType.REMOVE)
    protected List<Document> documentList;

    @ManyToMany(mappedBy = "docTypeList", cascade = CascadeType.REMOVE)
    protected List<Training> trainingList = new ArrayList<>();
}
