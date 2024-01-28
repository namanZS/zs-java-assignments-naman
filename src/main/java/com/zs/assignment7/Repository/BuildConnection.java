package com.zs.assignment7.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BuildConnection {
    private final Connection connection;
    public BuildConnection(){

        this.connection = connect();
    }
    public Connection connect() {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/collegedb";
        String username = "postgres";
        String password = "password";
        try {
            return DriverManager.getConnection(jdbcUrl, username, password);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public PreparedStatement prepareStatement(String query) throws SQLException {
        return connection.prepareStatement(query);

    }
}
