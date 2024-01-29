package com.zs.assignment10.service;

import com.zs.assignment10.model.Product;
import com.zs.assignment10.repository.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.*;
public class ProductService {
   ProductRepository productRepository=new ProductRepository();
    private static final Logger logger = LogManager.getLogger(BuildConnection.class);
   private static Connection con;
    public ProductService() throws SQLException {
        con= BuildConnection.getConnection();
        createTable();
    }

    public ProductService(Connection mockConnection, ProductRepository mockRepository) throws SQLException {
        con = mockConnection;
        this.productRepository = mockRepository;
    }

    public void createTable() throws SQLException {
        try{
        String createTableQuery="CREATE TABLE IF NOT EXISTS product ("
                + "id SERIAL PRIMARY KEY ,"
                + "name VARCHAR(255),"
                + "price DOUBLE PRECISION"
                + ")";
        PreparedStatement preparedStatement = con.prepareStatement(createTableQuery);
        preparedStatement.executeUpdate();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }
    public List<Product> getAllProducts() throws SQLException {
        List<Product> productList = new ArrayList<>();

        ResultSet result = productRepository.getAllProduct(con);
        while (result.next()) {
            int id = result.getInt("id");
            String name = result.getString("name");
            double price = result.getDouble("price");
            Product product = new Product(id, name, price);
            productList.add(product);
        }
        return productList;
    }
    public Product findProductById(int id) throws SQLException {

       ResultSet result = productRepository.findProductById(id,con);
       Product newProduct= null;

        if (result.next()) {
            String name = result.getString("name");
            double price = result.getDouble("price");
            newProduct =new Product(id, name, price);
        }
        return newProduct;

    }
    public boolean deleteProductById(int id) {

        return productRepository.deleteProductById(id,con);
    }
    public boolean checkIfProductExists(int id) throws SQLException {
        ResultSet result =  productRepository.checkIfProductExists(id,con);
        return result.next();
    }
    public Product saveProduct(String name, double price) throws SQLException {
        ResultSet generatedKeys= productRepository.insertProduct(name,price,con);
        if (generatedKeys.next()) {
            int id = generatedKeys.getInt(1);
            return new Product(id, name, price);
        }
        return null;
    }
}
