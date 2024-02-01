package com.zs.assignment7;
import com.zs.assignment7.controller.DepartmentController;
import com.zs.assignment7.controller.StudentController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;


public class Main{
    private static StudentController studentController;
    private static DepartmentController departmentController;
    static {
        try {
            studentController=new StudentController();
            departmentController=new DepartmentController();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void main(String[]args) throws SQLException {
        int count=100000;
        try{
            studentController.generateMillionRecords(count);
            departmentController.assignDepartments();
            studentController.exportIntoFile();
        }
        catch (SQLException e) {
            throw new SQLException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}
