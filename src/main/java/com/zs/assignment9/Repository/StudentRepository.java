package com.zs.assignment9.Repository;
import com.zs.assignment9.models.Student;

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

    public Student insertStudent(Student student) throws SQLException {
        String insertSQL = "INSERT INTO student (first_name, last_name) VALUES (?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.executeUpdate();
            return student;
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
    public Student getStudentById(int id)throws SQLException{
        String query = "SELECT s.id, s.first_name,s.last_name"+
                "FROM student s where s.id= "+id;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            return (Student) preparedStatement.executeQuery();
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}