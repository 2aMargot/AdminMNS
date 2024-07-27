package com.project.adminmns.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.adminmns.view.EmployeeView;
import com.project.adminmns.view.StudentView;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Represents an employee in the system, extending the base {@link ModelUser} class.
 * <p>
 * This class inherits from {@link ModelUser} and adds additional attributes specific to employees, such as
 * their department. The employee entity maps to a database table where employee-specific details are stored.
 * </p>
 *
 * @see ModelUser
 */
@Entity
@Data
public class Employee  extends ModelUser{

    @Column(name = "employee_department", length = 100)
    @NotBlank(message = "Le departement ne peut etre vide")
    @Size(min=8, max = 100,  message = "Departement minimum 8 caractères maximum 100 caractères")
    @JsonView(EmployeeView.class)
    protected String department;
}
