package com.zs.assignment7.Repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BuildConnection {
    private static final Logger logger = LogManager.getLogger(BuildConnection.class);
    private static final Properties properties = new Properties();
    public BuildConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {
            logger.info("PostgreSQL JDBC driver not found.");
            throw new SQLException("PostgreSQL JDBC driver not found.");
        }
    }
    public static Connection getConnection() throws SQLException{


        try (FileInputStream input = new FileInputStream("/Users/raramuri/Documents/Zopsmart/zs-java-assignments-naman/src/main/resources/dbconfigs.properties")) {
            properties.load(input);
        } catch (IOException e) {
            logger.error("DbConfig file not found!!");
            throw new SQLException(e.getMessage());

        }

        String dbUrl = properties.getProperty("db.url");
        String dbUsername = properties.getProperty("db.username");
        String dbPassword = properties.getProperty("db.password");
        try {
            return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (SQLException e) {
            logger.error("Db connection error!!");
            throw new SQLException(e.getMessage());

        }


    }
}
