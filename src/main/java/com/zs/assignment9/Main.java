package com.zs.assignment9;

import com.zs.assignment9.Controller.StudentController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * The type Main.
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Main.
     *
     * @param args the args
     * @throws SQLException the sql exception
     * @throws IOException  the io exception
     */
    public static void main(String[]args) throws SQLException, IOException {
        StudentController studentController=new StudentController();
        while(true){
            switch(scanner.nextInt()){
                case 1:
                    studentController.getStudent();
                    break;
                case 2:
                    studentController.createStudent();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Invalid input");
            }
        }




    }
}
