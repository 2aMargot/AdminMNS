package com.project.AdminMNS.dao;

import com.project.AdminMNS.model.AbsenceCause;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbsenceCauseDao extends JpaRepository<AbsenceCause, Integer> {
}
