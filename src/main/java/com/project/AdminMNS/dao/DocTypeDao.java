package com.project.AdminMNS.dao;

import com.project.AdminMNS.model.DocType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocTypeDao extends JpaRepository<DocType, Integer> {
}
