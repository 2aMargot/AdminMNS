package com.project.AdminMNS.dao;

import com.project.AdminMNS.model.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbsenceDao extends JpaRepository<Absence, Integer> {
}
