package com.zs.assignment9.services;

import com.zs.assignment9.Repository.StudentRepository;
import com.zs.assignment9.models.Student;

import java.io.IOException;
import java.sql.SQLException;

/**
 * The type Student service.
 */
public class StudentService {
    private final StudentRepository studentRepository;

    /**
     * Instantiates a new Student service.
     *
     * @throws SQLException the sql exception
     * @throws IOException  the io exception
     */
    public StudentService() throws SQLException, IOException {
        this.studentRepository=new StudentRepository();
    }

    /**
     * Instantiates a new Student service.
     *
     * @param studentRepository the student repository
     */
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Insert record student.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @return the student
     * @throws SQLException the sql exception
     */
    public Student insertRecord(String firstName, String lastName) throws SQLException {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        return studentRepository.insertStudent(student);
    }

    /**
     * Gets student.
     *
     * @param id the id
     * @return the student
     * @throws SQLException the sql exception
     */
    public Student getStudent(int id) throws SQLException {
       return studentRepository.getStudentById(id);
    }
}
