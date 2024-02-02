package com.zs.assignment9;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import com.zs.assignment9.Repository.StudentRepository;
import com.zs.assignment9.models.Student;
import com.zs.assignment9.services.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

/**
 * The type Student service test.
 */
@ExtendWith(MockitoExtension.class)

public class StudentServiceTest {

    @Mock
    private StudentRepository mockStudentRepository;

    @InjectMocks
    private StudentService studentService;

    private static Student newStudent;

    /**
     * Setup.
     */
    @BeforeAll
    public static void setup(){
        newStudent =new Student(111, "Naman", "Gupta");

    }

    /**
     * Test create student.
     *
     * @throws SQLException the sql exception
     */
    @Test
    public void testCreateStudent() throws SQLException {
        when(mockStudentRepository.insertStudent(any(Student.class))).thenReturn(newStudent);
        Student result = studentService.insertRecord(newStudent.getFirstName(), newStudent.getLastName());
        Assertions.assertNotNull(result);
        Assertions.assertEquals(newStudent.getFirstName(), result.getFirstName());
        Assertions.assertEquals(newStudent.getLastName(), result.getLastName());
        verify(mockStudentRepository, times(1)).insertStudent(any(Student.class));
    }

    /**
     * Test get student.
     *
     * @throws SQLException the sql exception
     */
    @Test
    public void testGetStudent() throws SQLException {

        when(mockStudentRepository.getStudentById(anyInt())).thenReturn(newStudent);
        Student result = studentService.getStudent(newStudent.getId());

        Assertions.assertNotNull(result);
        Assertions.assertEquals(newStudent.getId(), result.getId());
        Assertions.assertEquals(newStudent.getFirstName(), result.getFirstName());
        Assertions.assertEquals(newStudent.getLastName(), result.getLastName());

        verify(mockStudentRepository, times(1)).getStudentById(eq(newStudent.getId()));
    }
}
