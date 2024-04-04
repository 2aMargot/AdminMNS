package com.project.adminmns.dao;

import com.project.adminmns.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleDao extends JpaRepository<UserRole, Integer> {
}
