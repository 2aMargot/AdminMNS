package com.project.adminmns.dao;

import com.project.adminmns.model.DocType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocTypeDao extends JpaRepository<DocType, Integer> {
}
