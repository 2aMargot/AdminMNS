package com.project.adminmns.dao;

import com.project.adminmns.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingDao extends JpaRepository<Training, Integer> {
}
