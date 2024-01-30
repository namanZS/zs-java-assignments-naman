package com.zs.assignment9.services;

import com.zs.assignment9.Repository.StudentRepository;
import com.zs.assignment9.models.Student;

import java.sql.SQLException;

public class StudentService {
    private final StudentRepository studentRepository;
    public StudentService() {
        studentRepository=new StudentRepository();

    }

   public StudentService(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }
    public Student insertRecord(String firstName, String lastName) throws SQLException {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        return studentRepository.insertStudent(student);

    }
    public Student getStudent(int id) throws SQLException {

       return studentRepository.getStudentById(id);
    }
}