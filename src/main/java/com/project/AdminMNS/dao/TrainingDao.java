package com.project.AdminMNS.dao;

import com.project.AdminMNS.model.Student;
import com.project.AdminMNS.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingDao extends JpaRepository<Training, Integer> {
}
