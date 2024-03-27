package com.project.AdminMNS.dao;

import com.project.AdminMNS.model.Lateness;
import com.project.AdminMNS.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LatenessDao extends JpaRepository<Lateness, Integer> {
}
