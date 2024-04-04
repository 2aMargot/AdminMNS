package com.project.adminmns.dao;

import com.project.adminmns.model.Lateness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LatenessDao extends JpaRepository<Lateness, Integer> {
}
