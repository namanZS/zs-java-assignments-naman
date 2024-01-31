package com.zs.assignment7.controller;

import com.zs.assignment7.services.StudentService;

import java.sql.SQLException;

public class StudentController {
   private final StudentService studentService;
    public StudentController() {
        studentService=new StudentService();
    }

    public void generateMillionRecords(int records) throws SQLException {
        studentService.generateAndInsertData(records);
    }
    public void exportIntoFile() throws SQLException {
        studentService.exportIntoFile();
    }
}
