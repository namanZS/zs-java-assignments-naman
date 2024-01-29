package com.zs.assignment10.repository;

import com.zs.assignment10.model.Product;
import com.zs.assignment10.service.BuildConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.*;

public class ProductRepository {
    private static final Logger logger = LogManager.getLogger(BuildConnection.class);
    public ProductRepository() throws SQLException {
    }
    public ResultSet getAllProduct(Connection con){
    try{
        String Query = "SELECT id, name, price FROM product";
    PreparedStatement preparedStatement = con.prepareStatement(Query);
    return preparedStatement.executeQuery();

    } catch (SQLException e) {
        logger.error("Error in getting all Products!!");
    }
    return null;
}

    public ResultSet findProductById(int id, Connection con) {
        try {
            String findProductQuery = "SELECT id, name, price FROM product WHERE id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(findProductQuery);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            logger.error("Error in finding Product by ID!!");
        }
        return null;
    }

    public boolean deleteProductById(int id, Connection con) {
        try {
            String deleteProductQuery = "DELETE FROM product WHERE id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(deleteProductQuery);
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            logger.error("Error in deleting Product by ID!!");
        }

        return false;
    }

    public ResultSet checkIfProductExists(int id, Connection con) {
        try {
            String checkProductQuery = "SELECT id FROM product WHERE id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(checkProductQuery);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeQuery();

        } catch (SQLException e) {
            logger.error("Error in checking if Product exists!!");
        }
        return null;

    }

    public ResultSet insertProduct(String name, double price, Connection con) {
        try {
            String insertProductQuery = "INSERT INTO product (name, price) VALUES (?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertProductQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, price);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                return preparedStatement.getGeneratedKeys();

            }

        } catch (SQLException e) {
            logger.error("Error in inserting Product!!");
        }

        return null;
    }
}
