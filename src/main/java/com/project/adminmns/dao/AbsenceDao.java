package com.project.adminmns.dao;

import com.project.adminmns.model.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link Absence} entities.
 * <p>
 * This interface extends {@link JpaRepository}, providing basic CRUD operations
 * for {@link Absence} entities. It includes methods for saving, deleting, and finding
 * {@link Absence} entities by their primary key.
 * </p>
 */
@Repository
public interface AbsenceDao extends JpaRepository<Absence, Integer> {
}
