package com.zs.assignment9.Repository;

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
    private static Connection connection =null;

    public static Connection getConnection() throws SQLException{
        if(connection!=null) {
            return connection;
        }
        try (FileInputStream input = new FileInputStream("/Users/raramuri/Documents/Zopsmart/zs-java-assignments-naman/src/main/resources/dbconfigs.properties")) {
            properties.load(input);
        } catch (IOException e) {
            logger.error("DbConfig file not found!!");
        }

        String dbUrl = properties.getProperty("db.url2");
        String dbUsername = properties.getProperty("db.username");
        String dbPassword = properties.getProperty("db.password");
        try {
            return connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (SQLException e) {
            logger.error("Db connection error!!");
            throw new SQLException(e.getMessage());

        }


    }
}
