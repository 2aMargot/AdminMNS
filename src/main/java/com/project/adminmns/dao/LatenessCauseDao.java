package com.project.adminmns.dao;

import com.project.adminmns.model.LatenessCause;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LatenessCauseDao extends JpaRepository<LatenessCause, Integer> {
}
