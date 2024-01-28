package com.zs.assignment7.services;

import com.zs.assignment7.Main;
import com.zs.assignment7.Repository.StudentRepository;
import com.zs.assignment7.models.Department;
import com.zs.assignment7.models.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class StudentService {
    private static final Logger logger = LogManager.getLogger(Main.class);

    StudentRepository studentRepository=new StudentRepository();
    public void generateAndInsertData(int recordCount) throws SQLException {
        studentRepository.createStudentTable();
        logger.info("Generating and Inserting students records to db......");
        for (int i = 1; i <= recordCount; i++) {
            String firstName = "FName" + i;
            String lastName = "LName" + i;
            String mobile = "1234567890";

            Student student = new Student();
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setMobile(mobile);
            studentRepository.insertStudent(student);

        }
        logger.info("Inserted 1 million records successfully!!");
    }
    public void exportIntoFile() throws SQLException {
        ResultSet result = studentRepository.getStudentData();
        String filePath = "/Users/raramuri/Documents/Zopsmart/zs-java-assignments-naman/src/main/java/com/zs/assignment7/studentData.txt";
        if (!result.next()) {
            logger.info("No data found in ResultSet.");
            return;
        }
        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
            System.out.println("Data Fetched successfully....");
            logger.info("Data Fetched successfully....");
            logger.info("Writing to file......");

            while (result.next()) {
                int studentId = result.getInt("id");
                String studentName = result.getString("first_name") + " " + result.getString("last_name");
                int mobile = result.getInt("mobile");
                String departmentName = result.getString("name");

                // Write to the file
                fileWriter.write("Student ID: " + studentId +
                        ", Student Name: " + studentName +
                        ", Mobile: " + mobile +
                        ", Department Name: " + departmentName + "\n");
            }

            logger.info("Results written to " + filePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
}
}