package com.project.adminmns.dao;

import com.project.adminmns.model.Employee;
import com.project.adminmns.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer> {
}
