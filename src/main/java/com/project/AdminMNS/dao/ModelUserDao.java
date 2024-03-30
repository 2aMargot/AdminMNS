package com.project.AdminMNS.dao;

import com.project.AdminMNS.model.ModelUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelUserDao extends JpaRepository<ModelUser, Integer> {
}
