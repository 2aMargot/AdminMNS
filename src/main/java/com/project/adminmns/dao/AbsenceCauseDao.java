package com.project.adminmns.dao;

import com.project.adminmns.model.AbsenceCause;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbsenceCauseDao extends JpaRepository<AbsenceCause, Integer> {
}
