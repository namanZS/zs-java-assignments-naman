package com.zs.assignment7.services;

import com.zs.assignment7.Repository.BuildConnection;
import com.zs.assignment7.Repository.StudentRepository;
import com.zs.assignment7.models.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.zip.GZIPOutputStream;
import java.util.zip.GZIPInputStream;

public class StudentService {
    private final Connection connection;
    public StudentService(){
        try {
            connection= BuildConnection.getConnection();
        } catch (SQLException e) {
            logger.error("Error in making student db connection");
            throw new RuntimeException(e);

        }

    }
    private static final Logger logger = LogManager.getLogger(StudentService.class);

    private  final StudentRepository studentRepository=new StudentRepository();

    public void generateAndInsertData(int recordCount) throws SQLException {
        studentRepository.createStudentTable(connection);
        logger.info("Generating and Inserting students records to db......");
        for (int i = 1; i <= recordCount; i++) {
            String firstName = "FName" + i+1;
            String lastName = "LName" + i;
            String mobile = "100023456";

            Student student = new Student();
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setMobile(mobile);
            studentRepository.insertStudent(student,connection);

        }
        logger.info("Inserted 1 million records successfully!!");
    }
    public void exportIntoFile() throws SQLException {
        ResultSet result = studentRepository.getStudentData(connection);
        String filePath = "/Users/raramuri/Documents/Zopsmart/zs-java-assignments-naman/src/main/java/com/zs/assignment7/studentData.txt.gz";

        try (FileOutputStream fos = new FileOutputStream(filePath);
             GZIPOutputStream gzipOS = new GZIPOutputStream(fos);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(gzipOS))) {

            System.out.println("Data Fetched successfully....");
            logger.info("Data Fetched successfully....");
            logger.info("Writing to file......");

            while (result.next()) {
                int studentId = result.getInt("id");
                String studentName = result.getString("first_name") + " " + result.getString("last_name");
                int mobile = result.getInt("mobile");
                String departmentName = result.getString("name");
                writer.write(studentId + " " + studentName + " " + mobile + " " + departmentName + "\n");
            }

            writer.flush();
            logger.info("Results written to " + filePath);

        } catch (IOException e) {
            logger.error("Exported file not found!!");
            throw new RuntimeException(e);
        }
}
}
