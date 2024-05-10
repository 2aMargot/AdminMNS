package com.project.adminmns.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.adminmns.view.EmployeeView;
import com.project.adminmns.view.ModelUserView;
import com.project.adminmns.view.StudentView;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ModelUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @JsonView({ModelUserView.class, StudentView.class, EmployeeView.class})
    protected Integer id;

    @Column(name= "user_email",unique = true)
    @NotBlank(message = "L'email ne peut etre vide")
    @Email
    @JsonView({ModelUserView.class, StudentView.class, EmployeeView.class})
    protected String email;

    @Column(name = "user_password", length = 100)
    @Size(min=8, max = 100,  message = "Mot de passe minimum 8 caractères maximum 100 caractères")
    @NotBlank(message = "Le mot de passe ne peut etre vide")
    protected String password;

    @Size(min = 2, max = 100, message = "nom minimum 2 caractères et maximum 100 caractères")
    @Column(name = "user_lastname", length = 100)
    @NotBlank(message = "Le nom ne peut etre vide")
    @JsonView({ModelUserView.class, StudentView.class, EmployeeView.class})
    protected String lastname;

    @Size(min = 2, max = 100, message = "prénom minimum 2 caractères et maximum 100 caractères")
    @Column(name = "user_firstname", length = 100)
    @NotBlank(message = "Le prénom ne peut etre vide")
    @JsonView({ModelUserView.class, StudentView.class, EmployeeView.class})
    protected String firstname;

    @Column(name = "user_gender")
    @JsonView({ModelUserView.class, StudentView.class, EmployeeView.class})
    protected String gender;

    @Column(name= "user_is_enable",columnDefinition = "boolean default true")
    @JsonView({ModelUserView.class, StudentView.class, EmployeeView.class})
    protected boolean isEnable = true;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    @JsonView({ModelUserView.class, EmployeeView.class})
    protected UserRole role = new UserRole(2, "STUDENT", "Juste les droits étudiant");
}
