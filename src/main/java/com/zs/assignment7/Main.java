package com.zs.assignment7;
import com.zs.assignment7.controller.DepartmentController;
import com.zs.assignment7.controller.StudentController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class Main{

    public static void main(String[]args){
        StudentController studentController=new StudentController();
        DepartmentController departmentController=new DepartmentController();


        int count=1000000;
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