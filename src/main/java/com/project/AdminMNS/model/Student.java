package com.project.AdminMNS.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@PrimaryKeyJoinColumn(name = "user_id")
public class Student extends ModelUser{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "student_id")
//    protected Integer id;

    @Column(name = "student_birthdate")
    @NotBlank(message = "La date de naissance ne peut etre vide")
    protected LocalDate birthdate;

    @Column(name = "student_birthplace", length = 60)
    @NotBlank(message = "La ville de naissance ne peut etre vide")
    @Size(max = 60, message = "Le nom de la ville de naissance ne peux pas etre supérieur à maximum 60 caractères")
    protected String birthplace;

    @Column(name = "student_nationality", length = 100)
    @NotBlank(message = "La nationalité ne peut etre vide")
    @Size(max = 100, message = "La nationalité ne peux pas etre supérieur à maximum 100 caractères")
    protected String nationality;

    @Column(name = "student_postalcode", length = 20)
    @NotBlank(message = "Le code postal ne peut etre vide")
    @Size(max = 20, message = "Le code postal ne peux pas etre supérieur à maximum 20 caractères")
    protected String postalCode;

    @Column(name = "student_address")
    @NotBlank(message = "L'adresse ne peut etre vide")
    protected String address;

    @Column(name = "student_city", length = 100)
    @NotBlank(message = "La ville ne peut etre vide")
    @Size(max = 100, message = "La ville ne peux pas etre supérieur à maximum 100 caractères")
    protected String city;

    @Column(name = "student_phonenumber", length = 30)
    @Size(max = 30, message = "Le téléphone ne peux pas etre supérieur à maximum 30 caractères")
    protected String phoneNumber;

    @Column(name = "student_social_security_number", unique = true, length = 50)
    @Size(max = 50, message = "Le numéro de sécurité social ne peux pas etre supérieur à maximum 50 caractères")
    protected String socialSecurityNumber;

    @Column(name = "student_france_travail_number", unique = true, length = 30)
    @Size(max = 30, message = "Le numéro de france travail ne peux pas etre supérieur à maximum 30 caractères")
    protected String franceTravailNumber;

    @OneToMany(mappedBy = "studentAbsence")
    protected List<Absence> absenceList = new ArrayList<>();

    @OneToMany(mappedBy = "studentLateness")
    protected List<Lateness> latenessList = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    protected List<StudentInscriptionFolder> studentInscriptionFolderList = new ArrayList<>();

}
