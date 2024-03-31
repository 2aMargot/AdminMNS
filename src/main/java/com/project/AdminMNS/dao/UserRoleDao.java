package com.project.AdminMNS.dao;

import com.project.AdminMNS.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleDao extends JpaRepository<UserRole, Integer> {
}
