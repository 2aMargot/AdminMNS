package com.project.AdminMNS.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer studentId;

    protected LocalDate studentBirthdate;

    protected String studentBirthPlace;

    protected String studentNationality;

    protected String studentPostalCode;

    protected String studentAdress;

    protected String studentCity;

    protected String studentPhoneNumber;

    protected String socialSecurityNumber;

    protected String franceTravailNumber;


}
