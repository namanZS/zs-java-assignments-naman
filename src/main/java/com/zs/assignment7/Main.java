package com.zs.assignment7;
import com.zs.assignment7.controller.DepartmentController;
import com.zs.assignment7.controller.StudentController;

import java.sql.SQLException;
import java.util.Scanner;


public class Main{
    private static final StudentController studentController=new StudentController();
    private static final DepartmentController departmentController=new DepartmentController();
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[]args){
        System.out.println("Please enter the number of records you want to insert: ");
        int count=input.nextInt();
        try{
            studentController.GenerateMillionRecords(count);
            departmentController.AssignDepartments();
            studentController.ExportIntoFile();
        }
        catch (SQLException e) {

            throw new RuntimeException(e);
        }


    }


}
