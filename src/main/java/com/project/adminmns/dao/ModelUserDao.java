package com.project.adminmns.dao;

import com.project.adminmns.model.ModelUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing {@link ModelUser} entities.
 * <p>
 * This interface extends {@link JpaRepository}, providing basic CRUD operations
 * as well as additional query methods for {@link ModelUser} entities.
 * </p>
 */
@Repository
public interface ModelUserDao extends JpaRepository<ModelUser, Integer> {

    /**
     * Finds a {@link ModelUser} entity by its email.
     *
     * @param email The email of the {@link ModelUser} to find.
     * @return An {@link Optional} containing the {@link ModelUser} if found, or an empty {@link Optional} if not found.
     */
    Optional<ModelUser> findByEmail(String email);
}
