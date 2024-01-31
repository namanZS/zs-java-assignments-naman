package com.zs.assignment7;
import com.zs.assignment7.controller.DepartmentController;
import com.zs.assignment7.controller.StudentController;

import java.sql.SQLException;
import java.util.Scanner;


public class Main{
    private static StudentController studentController;
    private static DepartmentController departmentController;
    static {
        studentController=new StudentController();
        departmentController=new DepartmentController();
    }
    public static void main(String[]args){
        int count=100000;
        try{
            studentController.generateMillionRecords(count);
            departmentController.assignDepartments();
            studentController.exportIntoFile();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


}
