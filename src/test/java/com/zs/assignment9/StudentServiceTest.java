package com.zs.assignment9;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import com.zs.assignment9.Repository.StudentRepository;
import com.zs.assignment9.models.Student;
import com.zs.assignment9.services.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class StudentServiceTest {

    private StudentRepository mockStudentRepository;
    private StudentService studentService;

    @BeforeEach
    public void setup(){
        mockStudentRepository = mock(StudentRepository.class);
        studentService = new StudentService(mockStudentRepository);
    }

    @Test
    public void testCreateStudent() throws SQLException {
        String firstName = "Naman";
        String lastName = "Gupta";
        Student newStudent=new Student(111,firstName,lastName);
        when(mockStudentRepository.insertStudent(any(Student.class))).thenReturn(newStudent);
        Student result = studentService.insertRecord(firstName, lastName);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(firstName, result.getFirstName());
        Assertions.assertEquals(lastName, result.getLastName());
        verify(mockStudentRepository, times(1)).insertStudent(any(Student.class));
    }

    @Test
    public void testGetStudent() throws SQLException {
        int studentId=111;
        Student resultStudent= new Student(111,"Naman","Gupta");
        when(mockStudentRepository.getStudentById(anyInt())).thenReturn(resultStudent);
        Student result = studentService.getStudent(studentId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(studentId, result.getId());
        Assertions.assertEquals("Naman",result.getFirstName());
        Assertions.assertEquals("Gupta",result.getLastName());

        verify(mockStudentRepository, times(1)).getStudentById(eq(studentId));
    }
}
