package com.project.adminmns.dao;

import com.project.adminmns.model.Lateness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link Lateness} entities.
 * <p>
 * This interface extends {@link JpaRepository}, providing basic CRUD operations
 * for {@link Lateness} entities. It includes methods for saving, deleting, and finding
 * {@link Lateness} entities by their primary key.
 * </p>
 */
@Repository
public interface LatenessDao extends JpaRepository<Lateness, Integer> {
}
