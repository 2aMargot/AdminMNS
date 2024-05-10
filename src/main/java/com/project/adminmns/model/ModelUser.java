package com.project.adminmns.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.adminmns.view.ModelUserView;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@Table(name = "model_user")
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@NoArgsConstructor
public class ModelUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    protected Integer id;

    @Column(name= "user_email",unique = true)
    @NotBlank(message = "L'email ne peut etre vide")
    @Email
    @JsonView(ModelUserView.class)
    protected String email;

    @Column(name = "user_password", length = 100)
    @Size(max = 100, min=8, message = "Mot de passe minimum 8 caractères maximum 100 caractères")
    @NotBlank(message = "Le mot de passe ne peut etre vide")
    protected String password;

    @Size(min = 2, max = 100, message = "nom minimum 2 caractères et maximum 100 caractères")
    @Column(name = "user_lastname", length = 100)
    @NotBlank(message = "Le nom ne peut etre vide")
    @JsonView(ModelUserView.class)
    protected String lastname;

    @Size(min = 2, max = 100, message = "prénom minimum 2 caractères et maximum 100 caractères")
    @Column(name = "user_firstname")
    @NotBlank(message = "Le prénom ne peut etre vide")
    @JsonView(ModelUserView.class)
    protected String firstname;

    @Column(name = "user_gender")
    protected String gender;

    @Column(name= "user_is_enable",columnDefinition = "boolean default true")
    @JsonView(ModelUserView.class)
    protected boolean isEnable = true;

    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id")
    @JsonView(ModelUserView.class)
    protected UserRole role = new UserRole(2, "STUDENT", "Juste les droits étudiant");


}
