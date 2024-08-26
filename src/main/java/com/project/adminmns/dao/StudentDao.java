package com.project.adminmns.dao;

import com.project.adminmns.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing {@link Student} entities.
 * <p>
 * This interface extends {@link JpaRepository}, providing basic CRUD operations
 * for {@link Student} entities. It inherits methods for saving, deleting, and finding
 * {@link Student} entities by their primary key.
 * </p>
 */
@Repository
public interface StudentDao extends JpaRepository<Student, Integer> {
    Optional<Student> findByEmail(String email);
}
