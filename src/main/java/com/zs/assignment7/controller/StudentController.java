package com.zs.assignment7.controller;

import com.zs.assignment7.services.StudentService;

import java.sql.SQLException;

public class StudentController {
   private static final StudentService studentService=new StudentService();

    public  void GenerateMillionRecords(int records) throws SQLException {
        studentService.generateAndInsertData(records);
    }
    public void ExportIntoFile() throws SQLException {
        studentService.exportIntoFile();
    }
}
