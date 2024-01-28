package com.zs.assignment7.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class DepartmentRepository {
    private Connection connection;


    public DepartmentRepository(){
        String jdbcUrl = "jdbc:postgresql://localhost:5432/collegedb";
        String username = "postgres";
        String password = "password";
        try {
            connection= DriverManager.getConnection(jdbcUrl, username, password);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public void createDepartmentTable() throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS department (" +
                "id SERIAL PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(createTableSQL);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createDepartments() throws SQLException {
        insertDepartment("CS");
        insertDepartment("EE");
        insertDepartment("Mech");
    }
    private void insertDepartment(String departmentName) {
        String insertQuery = "INSERT INTO department (name) VALUES (?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, departmentName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting department: " + e.getMessage(), e);
        }
    }
    public  void assignDepartments() throws SQLException {
        String updateQuery = "UPDATE student SET department_id = ? WHERE id = ?";
        Random random = new Random();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            for (int studentId = 1; studentId <= 1000000; studentId++) {
                int departmentId = random.nextInt(3);

                preparedStatement.setInt(1, departmentId);
                preparedStatement.setInt(2, studentId);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
