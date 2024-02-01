package com.zs.assignment10.repository;

import com.zs.assignment10.model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private static final Logger logger = LogManager.getLogger(ProductRepository.class);
    public List<Product> getAllProduct(Connection con) throws SQLException {
        List<Product>productList=new ArrayList<>();
    try{

        String Query = "SELECT id, name, price FROM product";
    PreparedStatement preparedStatement = con.prepareStatement(Query);

    ResultSet result =preparedStatement.executeQuery();
        while (result.next()) {
            int id = result.getInt("id");
            String name = result.getString("name");
            double price = result.getDouble("price");
            Product product = new Product(id, name, price);
            productList.add(product);
        }
        return productList;

    } catch (SQLException e) {
        logger.error("Error in getAllProduct from repository layer");
        throw new SQLException("Error in getting all Products!!");
    }
}
    public void createTable(Connection con) throws SQLException {
        try{
            String createTableQuery="CREATE TABLE IF NOT EXISTS product ("
                    + "id SERIAL PRIMARY KEY ,"
                    + "name VARCHAR(255),"
                    + "price DOUBLE PRECISION"
                    + ")";
            PreparedStatement preparedStatement = con.prepareStatement(createTableQuery);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error in creating product table");
        }
    }

    public Product findProductById(int id, Connection con) throws SQLException {
        if(!checkIfProductExists(id,con)) return new Product();
        try {
            String findProductQuery = "SELECT id, name, price FROM product WHERE id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(findProductQuery);
            preparedStatement.setInt(1, id);
            ResultSet result= preparedStatement.executeQuery();
            result.next();
            String name= result.getString("name");
            double price=result.getDouble("price");
            return new Product(id,name,price);
        } catch (SQLException e) {
            logger.error("Error in finding Product by ID!!");
            throw new SQLException("Error in finding Product by ID!!");
        }
    }

    public boolean deleteProductById(int id, Connection con) throws SQLException {
        if(!checkIfProductExists(id,con))return false;
        try {
            String deleteProductQuery = "DELETE FROM product WHERE id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(deleteProductQuery);
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            logger.error("Error in deleting Product by ID!!");
            throw new SQLException("Error in deleting Product by ID!!");
        }
    }

    public boolean checkIfProductExists(int id, Connection con) throws SQLException {
        try {
            String checkProductQuery = "SELECT id FROM product WHERE id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(checkProductQuery);
            preparedStatement.setInt(1, id);
            ResultSet result=preparedStatement.executeQuery();
           return result.next();

        } catch (SQLException e) {
            logger.error("Error in checking if Product exists!!");
            throw new SQLException("Error in checking if Product exists!!");
        }

    }

    public Product insertProduct(String name, double price, Connection con) throws SQLException {
        Product product=null;
        try {
            createTable(con);
            String insertProductQuery = "INSERT INTO product (name, price) VALUES (?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertProductQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, price);
            preparedStatement.executeUpdate();

            ResultSet keys =preparedStatement.getGeneratedKeys();
            keys.next();
             int id= keys.getInt(1);
             return new Product(id,name,price);
        } catch (SQLException e) {
            logger.error("Error in inserting Product!!");
            throw new SQLException("Error in inserting Product!!");
        }

    }
}
