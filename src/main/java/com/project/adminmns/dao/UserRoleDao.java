package com.project.adminmns.dao;

import com.project.adminmns.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link UserRole} entities.
 * <p>
 * This interface extends {@link JpaRepository}, providing basic CRUD operations
 * for {@link UserRole} entities. It inherits methods for saving, deleting, and finding
 * {@link UserRole} entities by their primary key.
 * </p>
 */
@Repository
public interface UserRoleDao extends JpaRepository<UserRole, Integer> {
}
