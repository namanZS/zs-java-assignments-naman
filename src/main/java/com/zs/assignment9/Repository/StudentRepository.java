package com.zs.assignment9.Repository;
import com.zs.assignment4.Main;
import com.zs.assignment9.models.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.processing.SQL;

import java.io.IOException;
import java.sql.*;

/**
 * The type Student repository.
 */
public class StudentRepository {
    private static Connection  connection;
    private static final Logger logger = LogManager.getLogger(StudentRepository.class);

    /**
     * Instantiates a new Student repository.
     *
     * @throws SQLException the sql exception
     * @throws IOException  the io exception
     */
    public StudentRepository() throws SQLException, IOException {
        try {
            connection= BuildConnection.getConnection();

        } catch (SQLException e) {
            logger.error("Error in creating database connection");
            throw new SQLException(e);

        } catch (IOException e) {
            throw new IOException(e);
        }

    }

    /**
     * Insert student student.
     *
     * @param student the student
     * @return the student
     * @throws SQLException the sql exception
     */
    public Student insertStudent(Student student) throws SQLException {
        String insertSQL = "INSERT INTO student (first_name, last_name) VALUES (?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.executeUpdate();

            return student;
        } catch (SQLException e) {
            logger.error("Error in inserting student");
            throw new SQLException(e);

        }
    }

    /**
     * Gets student by id.
     *
     * @param id the id
     * @return the student by id
     * @throws SQLException the sql exception
     */
    public Student getStudentById(int id)throws SQLException{
        String query = "SELECT s.id, s.first_name,s.last_name"+
                "FROM student s where s.id= "+id;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            return (Student) preparedStatement.executeQuery();
        }catch (SQLException e) {
            logger.error("Error in getting student by id ");
            throw new SQLException(e);
        }
    }
}
