package com.zs.assignment7.Repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Random;

public class DepartmentRepository {
    private final Connection connection;
    private static final Logger logger = LogManager.getLogger(StudentRepository.class);


    public DepartmentRepository() throws SQLException {
try{
    connection =BuildConnection.getConnection();
} catch (SQLException e) {
    logger.error("Connection error");
    throw new SQLException("Connection error");
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
            logger.error("Error in creating department table");
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
            logger.error("Inserting department record");
            throw new RuntimeException("Error inserting department: " + e.getMessage(), e);
        }
    }

    public void assignDepartments() throws SQLException {
        String selectQuery = "SELECT id FROM student";
        String updateQuery = "UPDATE student SET department_id = ? WHERE id = ?";
        Random random = new Random();

        try {

            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = selectStatement.executeQuery();


            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            while (resultSet.next()) {
                int studentId = resultSet.getInt("id");
                int departmentId = random.nextInt(3)+1;

                updateStatement.setInt(1, departmentId);
                updateStatement.setInt(2, studentId);

                updateStatement.executeUpdate();
            }
        } catch (SQLException e) {
            logger.error("Error in assigning Departments");
            throw new RuntimeException(e);
        }
    }

}
