package com.zs.assignment10.service;

import com.zs.assignment10.model.Product;
import com.zs.assignment10.repository.BuildConnection;
import com.zs.assignment10.repository.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.*;
public class ProductService {
    private final ProductRepository productRepository;
    private static final Logger logger = LogManager.getLogger(BuildConnection.class);
    private Connection con;
    public ProductService() {
        productRepository=new ProductRepository();
        setup();
    }
    void setup()  {
        try {
            con = BuildConnection.getConnection();
        }catch (SQLException e) {
            logger.error("Error in making Product db connection");

        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
    public ProductService(ProductRepository productRepository,Connection conn){
        this.productRepository=productRepository;
        this.con =conn;
    }

    public List<Product> getAllProducts() throws SQLException {
        return productRepository.getAllProduct(con);
    }
    public Product findProductById(int id) throws SQLException {
        return productRepository.findProductById(id,con);
    }
    public boolean deleteProductById(int id) throws SQLException {
        return productRepository.deleteProductById(id,con);
    }
    public boolean checkIfProductExists(int id) throws SQLException {
        return productRepository.checkIfProductExists(id,con);
    }
    public Product saveProduct(String name, double price) throws SQLException {
        return productRepository.insertProduct(name,price,con);
    }

}
