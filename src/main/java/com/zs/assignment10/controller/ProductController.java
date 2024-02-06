package com.zs.assignment10.controller;

import com.zs.assignment10.model.Product;
import com.zs.assignment10.service.ProductService;
import com.zs.assignment5.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
public class ProductController {
    private final Scanner scanner;
    private final Logger logger;
    private final ProductService productservice;

    public ProductController() {

        scanner= new Scanner(System.in);
        productservice=new ProductService();
        logger= LogManager.getLogger(Main.class);
    }

    public void findAllProducts() throws SQLException {
        List<Product> productsList=new ArrayList<>();
        try{
            productsList=productservice.getAllProducts();

        }catch(SQLException e){
            logger.error(e);
            throw new SQLException(e);
        }

        if(productsList.isEmpty()){
            System.out.println("No products available!!");
            return;
        }
        System.out.println("Available Products: ");
        for (Product product:productsList){
            System.out.println(product);

        }
    }
    public void findProductById() throws SQLException {
        System.out.println("Enter the Product Id: ");
        int id=readIntValue();
        Product fetchedProduct;
        try {
            fetchedProduct=productservice.findProductById(id);

            if(fetchedProduct==null) System.out.println("Product id not found!!");
            else System.out.println(fetchedProduct);
        }
        catch(SQLException e){
            logger.error(e);
            throw new SQLException(e);
        }


    }

    public void saveProduct() throws SQLException {
        System.out.println("Enter the details:");
        String name=readProductName();
        double price=readProductPrice();
        Product fetchedProduct;
        try{
            fetchedProduct=productservice.saveProduct(name,price);
            System.out.println(fetchedProduct);
        }
        catch(SQLException e){
            logger.error(e);
            throw new SQLException(e);
        }


    }
    public void deleteProductById() throws SQLException {
        System.out.println("Enter the Product Id: ");
        int id=readIntValue();
        try{
            boolean successDeleted=productservice.deleteProductById(id);
            if(successDeleted){
                System.out.println("product Deleted!!");
            }
            else{
                System.out.println("Product not found!!");
            }
        }
        catch(SQLException e){
            logger.error(e);
            throw new SQLException(e);
        }

    }
    public void checkIfProductExists() throws SQLException {
        System.out.println("Enter the Product Id: ");
        int id = readIntValue();
        try{
            boolean productFound=productservice.checkIfProductExists(id);
            if(productFound){
                System.out.println("Found product!!");
            }
            else{
                System.out.println("Product not found");
            }

        }
        catch(SQLException e){
            logger.error(e);
            throw new SQLException(e);
        }


    }
    private String readProductName() {
        String name;
        do {
            System.out.print("Enter the name of product: ");
            name = scanner.next().trim();

            if (name.isEmpty()) {
                System.out.println("Product name cannot be empty. Please enter a valid name.");
            }

        } while (name.isEmpty());

        return name;
    }

    private double readProductPrice() {
        double price;
        do {
            System.out.print("Enter the price: ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Invalid input. Please enter a valid price (numeric value).");
            }
            price = scanner.nextDouble();

            if (price < 0) {
                System.out.println("Price cannot be negative. Please enter a non-negative price.");
            }

        } while (price < 0);

        return price;
    }
    private int readIntValue() {
        int value;
        do {
            while (!scanner.hasNextDouble()) {
                System.out.println("Invalid input. Please enter a numeric value .");
                scanner.next();
            }
            value = scanner.nextInt();
            if (value < 0) {
                System.out.println(" Please enter a non-negative value.");
            }
        } while (value < 0);
        return value;
    }
}
