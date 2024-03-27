package com.project.AdminMNS.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Entity
@Data
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer roleId;

    @Size(min = 2, max = 100, message = "nom minimum 2 caractères et maximum 100 caractères")
    @Column(name = "role_name")
    protected String name;

    @Column(name = "role_description", columnDefinition = "TEXT")
    protected String description;
}
