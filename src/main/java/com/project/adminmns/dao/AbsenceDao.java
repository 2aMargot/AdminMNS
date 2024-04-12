package com.project.adminmns.dao;

import com.project.adminmns.model.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbsenceDao extends JpaRepository<Absence, Integer> {
}
