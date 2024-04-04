package com.project.adminmns.dao;

import com.project.adminmns.model.ModelUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModelUserDao extends JpaRepository<ModelUser, Integer> {

    Optional<ModelUser> findByEmail(String email);
}
