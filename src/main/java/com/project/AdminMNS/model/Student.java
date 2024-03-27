package com.project.AdminMNS.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    protected Integer id;

    @Column(name = "student_birthdate")
    protected LocalDate birthdate;

    @Column(name = "student_birthplace")
    protected String birthplace;

    @Column(name = "student_nationality")
    protected String nationality;

    @Column(name = "student_postalcode")
    protected String postalCode;

    @Column(name = "student_address")
    protected String address;

    @Column(name = "student_city")
    protected String city;

    @Column(name = "student_phonenumber")
    protected String phoneNumber;

    @Column(name = "student_social_security_number", unique = true)
    protected String socialSecurityNumber;

    @Column(name = "student_france_travail_number", unique = true)
    protected String franceTravailNumber;


}
