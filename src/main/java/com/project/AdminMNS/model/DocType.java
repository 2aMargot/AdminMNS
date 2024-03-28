package com.project.AdminMNS.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class DocType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctype_id")
    protected Integer id;

    @Column(name = "doctype_name")
    protected String name;

    @Column(name = "doctype_description", columnDefinition = "TEXT")
    protected String description;

    @OneToMany(mappedBy = "docType")
    protected List<Document> documentList;
}
