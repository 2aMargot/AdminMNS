package com.project.AdminMNS.dao;

import com.project.AdminMNS.model.StudentInscriptionFolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentInscriptionFolderDao extends JpaRepository<StudentInscriptionFolder, Integer> {
}
