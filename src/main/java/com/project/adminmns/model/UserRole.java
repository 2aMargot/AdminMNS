package com.project.adminmns.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.adminmns.view.ModelUserView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a user role in the system, defining the different roles that users can have.
 * <p>
 * This class maps to the "user_role" table in the database and contains information about roles, including
 * the role's name and description.
 * There is no possibility of CRUD for this entity.
 * If you want to add a new role you need to add a new personnalised annotation in security and and this annotation in the Controllers
 * </p>
 *
 * @see ModelUser
 * @see com.project.adminmns.security.AdminPermission
 * @see com.project.adminmns.security.DirectorPermission
 * @see com.project.adminmns.security.StudentPermission
 * @see com.project.adminmns.security.ValidatorPermission
 */
@Entity
@Data
@Table(name = "user_role")
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    protected Integer roleId;

    @Size(min = 2, max = 100, message = "nom minimum 2 caractères et maximum 100 caractères")
    @NotBlank(message = "Le nom du role ne peut etre vide")
    @Column(name = "role_name", length = 100)
    @JsonView(ModelUserView.class)
    protected String name;

    @Column(name = "role_description", columnDefinition = "TEXT")
    @JsonView(ModelUserView.class)
    protected String description;

//    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
//    protected List<ModelUser> userList = new ArrayList<>();
}
