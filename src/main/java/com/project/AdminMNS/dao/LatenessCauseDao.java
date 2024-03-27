package com.project.AdminMNS.dao;

import com.project.AdminMNS.model.Lateness;
import com.project.AdminMNS.model.LatenessCause;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LatenessCauseDao extends JpaRepository<LatenessCause, Integer> {
}
