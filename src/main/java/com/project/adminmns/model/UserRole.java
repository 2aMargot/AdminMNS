package com.project.adminmns.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Entity
@Data
@Table(name = "user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    protected Integer roleId;

    @Size(min = 2, max = 100, message = "nom minimum 2 caractères et maximum 100 caractères")
    @NotBlank(message = "Le nom du role ne peut etre vide")
    @Column(name = "role_name", length = 100)
    protected String name;

    @Column(name = "role_description", columnDefinition = "TEXT")
    protected String description;
}
