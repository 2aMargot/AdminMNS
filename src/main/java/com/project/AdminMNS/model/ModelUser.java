package com.project.AdminMNS.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Entity
@Data
public class ModelUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer userId;

    @Column(unique = true)
    protected String userEmail;

    @Column(unique = true)
    protected String userInternEmail;

    protected String userPassword;

    @Size(min = 2, max = 100, message = "nom minimum 2 caractères et maximum 100 caractères")
    protected String userLastname;

    @Size(min = 2, max = 100, message = "nom minimum 2 caractères et maximum 100 caractères")
    protected String userFirstname;

    protected String userGender;

    @Column(columnDefinition = "boolean default true")
    protected boolean userIsEnable;
}
