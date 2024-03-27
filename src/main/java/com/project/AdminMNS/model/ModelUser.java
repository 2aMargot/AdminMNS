package com.project.AdminMNS.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Entity
@Data
public class ModelUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    protected Integer id;

    @Column(name= "user_email",unique = true)
    protected String email;

    @Column(name= "user_intern_email",unique = true)
    protected String internEmail;

    @Column(name = "user_password")
    protected String password;

    @Size(min = 2, max = 100, message = "nom minimum 2 caractères et maximum 100 caractères")
    @Column(name = "user_lastname")
    protected String lastname;

    @Size(min = 2, max = 100, message = "nom minimum 2 caractères et maximum 100 caractères")
    @Column(name = "user_firstname")
    protected String firstname;

    @Column(name = "user_gender")
    protected String gender;

    @Column(name= "user_is_enable",columnDefinition = "boolean default true")
    protected boolean isEnable;
}
