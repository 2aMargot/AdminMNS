package com.project.adminmns.dao;

import com.project.adminmns.model.StudentInscriptionFolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentInscriptionFolderDao extends JpaRepository<StudentInscriptionFolder, Integer> {
}
