package com.zs.assignment7.controller;

import com.zs.assignment7.services.StudentService;

import java.io.IOException;
import java.sql.SQLException;

public class StudentController {
   private final StudentService studentService;
    public StudentController() throws SQLException, IOException {
        studentService=new StudentService();
    }

    public void generateMillionRecords(int records) throws SQLException {
        studentService.generateAndInsertData(records);
    }
    public void exportIntoFile() throws SQLException, IOException {
        studentService.exportIntoFile();
    }
}
