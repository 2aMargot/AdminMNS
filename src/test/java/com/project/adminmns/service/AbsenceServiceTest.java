package com.project.adminmns.service;

import com.project.adminmns.dao.AbsenceDao;
import com.project.adminmns.dao.AbsenceCauseDao;
import com.project.adminmns.model.Absence;
import com.project.adminmns.model.AbsenceCause;
import com.project.adminmns.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class AbsenceServiceTest {

    @Mock
    private AbsenceDao absenceDao;

    @Mock
    private AbsenceCauseDao absenceCauseDao;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private AbsenceService absenceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void absenceList() {
        // Arrange
        List<Absence> expectedAbsences = Arrays.asList(new Absence(), new Absence());
        when(absenceDao.findAll()).thenReturn(expectedAbsences);

        // Act
        List<Absence> actualAbsences = absenceService.absenceList();

        // Assert
        assertEquals(expectedAbsences, actualAbsences);
        verify(absenceDao, times(1)).findAll();
    }

    @Test
    void getAbsence_found() {
        // Arrange
        Absence expectedAbsence = new Absence();
        when(absenceDao.findById(anyInt())).thenReturn(Optional.of(expectedAbsence));

        // Act
        ResponseEntity<Absence> response = absenceService.getAbsence(1);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedAbsence, response.getBody());
        verify(absenceDao, times(1)).findById(1);
    }

    @Test
    void getAbsence_notFound() {
        // Arrange
        when(absenceDao.findById(anyInt())).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Absence> response = absenceService.getAbsence(1);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(absenceDao, times(1)).findById(1);
    }

    @Test
    void addAbsence_withExistingId() {
        // Arrange
        Absence existingAbsence = new Absence();
        existingAbsence.setId(1);
        when(absenceDao.findById(anyInt())).thenReturn(Optional.of(existingAbsence));

        // Act
        ResponseEntity<Absence> response = absenceService.addAbsence(existingAbsence);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(existingAbsence, response.getBody());
        verify(absenceDao, times(1)).findById(1);
        verify(absenceDao, times(1)).save(existingAbsence);
    }

    @Test
    void addAbsence_withNewId() {
        // Arrange
        Absence newAbsence = new Absence();
        Student student = new Student();
        newAbsence.setStudentAbsence(student);
        when(studentService.getStudentByEmail(anyString())).thenReturn(newAbsence.getStudentAbsence());

        // Act
        ResponseEntity<Absence> response = absenceService.addAbsence(newAbsence);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newAbsence, response.getBody());
        verify(absenceDao, times(1)).save(newAbsence);
    }

    @Test
    void updateAbsence_found() {
        // Arrange
        Absence existingAbsence = new Absence();
        Student student = new Student();
        existingAbsence.setStudentAbsence(student);
        existingAbsence.setId(1);
        when(absenceDao.findById(anyInt())).thenReturn(Optional.of(existingAbsence));
        when(studentService.getStudentByEmail(anyString())).thenReturn(existingAbsence.getStudentAbsence());

        // Act
        ResponseEntity<Absence> response = absenceService.updateAbsence(existingAbsence, 1);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(absenceDao, times(1)).save(existingAbsence);
    }

    @Test
    void updateAbsence_notFound() {
        // Arrange
        Absence updatedAbsence = new Absence();
        when(absenceDao.findById(anyInt())).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Absence> response = absenceService.updateAbsence(updatedAbsence, 1);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(absenceDao, never()).save(updatedAbsence);
    }

    @Test
    void deleteAbsence_found() {
        // Arrange
        Absence existingAbsence = new Absence();
        when(absenceDao.findById(anyInt())).thenReturn(Optional.of(existingAbsence));

        // Act
        ResponseEntity<Absence> response = absenceService.deleteAbsence(1);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(absenceDao, times(1)).deleteById(1);
    }

    @Test
    void deleteAbsence_notFound() {
        // Arrange
        when(absenceDao.findById(anyInt())).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Absence> response = absenceService.deleteAbsence(1);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(absenceDao, never()).deleteById(anyInt());
    }


    @Test
    void absenceCauseList() {
        // Arrange
        List<AbsenceCause> expectedAbsenceCauses = Arrays.asList(new AbsenceCause(), new AbsenceCause());
        when(absenceCauseDao.findAll()).thenReturn(expectedAbsenceCauses);

        // Act
        List<AbsenceCause> actualAbsenceCauses = absenceService.absenceCauseList();

        // Assert
        assertEquals(expectedAbsenceCauses, actualAbsenceCauses);
        verify(absenceCauseDao, times(1)).findAll();
    }
}
