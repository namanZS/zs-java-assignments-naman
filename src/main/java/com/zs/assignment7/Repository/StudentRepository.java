package com.zs.assignment7.Repository;
import com.zs.assignment7.models.Student;
import com.zs.assignment7.services.StudentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;

import java.io.IOException;
import java.sql.*;

public class StudentRepository {
    private static final Logger logger = LogManager.getLogger(StudentRepository.class);
    public StudentRepository(){
    }
    public void createStudentTable(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS student (" +
                "id SERIAL PRIMARY KEY," +
                "first_name VARCHAR(255)," +
                "last_name VARCHAR(255)," +
                "mobile VARCHAR(20)," +
                "department_id INTEGER)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(createTableSQL);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error("Error in creating student table!!");
            throw new SQLException("Error in creating student table!!");
        }
    }

    public void insertStudent(Student student,Connection connection) throws SQLException {
        String insertSQL = "INSERT INTO student (first_name, last_name, mobile) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setString(3, student.getMobile());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error iin inserting student!!");
            throw new SQLException("Error iin inserting student!!");
        }
    }
    public ResultSet getStudentData(Connection connection)throws SQLException{
        String query = "SELECT s.id, s.first_name,s.last_name, s.mobile , d.name " +
                "FROM student s " +
                "JOIN department d ON s.department_id = d.id order by s.id";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            return preparedStatement.executeQuery();
        }catch (SQLException e) {
           logger.error("Error in getting student data");
           throw new SQLException("Error in getting student data");
        }
    }
}
