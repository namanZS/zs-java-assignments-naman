package com.zs.assignment7.Repository;
import com.zs.assignment7.models.Student;
import java.io.FileWriter;

import java.io.IOException;
import java.sql.*;

public class StudentRepository {
    private static Connection  connection;
    public StudentRepository(){
        String jdbcUrl = "jdbc:postgresql://localhost:5432/collegedb";
        String username = "postgres";
        String password = "password";
        try {
            connection= DriverManager.getConnection(jdbcUrl, username, password);

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }
    public void createStudentTable() throws SQLException {
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
            throw new RuntimeException(e);
        }
    }

    public void insertStudent(Student student) throws SQLException {
        String insertSQL = "INSERT INTO student (first_name, last_name, mobile) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setString(3, student.getMobile());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ResultSet getStudentData()throws SQLException{
        String query = "SELECT s.id, s.first_name,s.last_name, s.mobile , d.name " +
                "FROM student s " +
                "JOIN department d ON s.department_id = d.id";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            return preparedStatement.executeQuery();
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}