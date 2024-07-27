package com.project.adminmns.dao;

import com.project.adminmns.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link Document} entities.
 * <p>
 * This interface extends {@link JpaRepository}, providing basic CRUD operations
 * for {@link Document} entities. It includes methods for saving, deleting, and finding
 * {@link Document} entities by their primary key.
 * </p>
 */
@Repository
public interface DocumentDao extends JpaRepository<Document, Integer> {
}
