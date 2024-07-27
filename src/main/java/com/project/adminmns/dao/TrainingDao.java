package com.project.adminmns.dao;

import com.project.adminmns.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link Training} entities.
 * <p>
 * This interface extends {@link JpaRepository}, providing basic CRUD operations
 * for {@link Training} entities. It inherits methods for saving, deleting, and finding
 * {@link Training} entities by their primary key.
 * </p>
 */
@Repository
public interface TrainingDao extends JpaRepository<Training, Integer> {
}
