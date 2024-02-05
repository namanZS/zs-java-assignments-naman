package com.zs.assignment9.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import com.zs.assignment9.services.StudentService;

/**
 * The type Student controller.
 */
public class StudentController {
    private final StudentService studentService;
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Instantiates a new Student controller.
     *
     * @throws SQLException the sql exception
     * @throws IOException  the io exception
     */
    public StudentController() throws SQLException, IOException {
        studentService=new StudentService();
    }

    /**
     * Create student.
     *
     * @throws SQLException the sql exception
     */
    public void createStudent() throws SQLException {
        System.out.println("Enter firstname: ");
        String firstName=scanner.next();
        System.out.println("Enter lastname: ");
        String lastName=scanner.next();
        studentService.insertRecord(firstName,lastName);

    }

    /**
     * Gets student.
     *
     * @throws SQLException the sql exception
     */
    public void getStudent() throws SQLException {
        System.out.println("Enter id of student: ");

        int id=scanner.nextInt();
        studentService.getStudent(id);

    }

}
