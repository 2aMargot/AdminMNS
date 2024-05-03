package com.project.adminmns.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Employee  extends ModelUser{

    @Column(name = "employee_department", length = 100)
    @NotBlank(message = "Le departement ne peut etre vide")
    @Size(min=8, max = 100,  message = "Departement minimum 8 caractères maximum 100 caractères")
    protected String department;
}
