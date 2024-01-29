package com.zs.assignment10.controller;

import com.zs.assignment10.model.Product;
import com.zs.assignment10.service.ProductService;
import java.sql.SQLException;
import java.util.*;
public class ProductController {
    static Scanner scanner = new Scanner(System.in);
    ProductService productservice=new ProductService();

    public ProductController() throws SQLException {
    }

    public void findAllProducts() throws SQLException {

        List<Product> productsList=productservice.getAllProducts();
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
        Product fetchedProduct=productservice.findProductById(id);
    System.out.println(fetchedProduct);

}

public void saveProduct() throws SQLException {
    System.out.println("Enter the details:");

    System.out.print("Enter the name of product: ");
    String name=readProductName();
    System.out.print("Enter the price: ");
    double price=readProductPrice();

    Product fetchedProduct=productservice.saveProduct(name,price);
    System.out.println(fetchedProduct);
}
public void deleteProductById(){
    System.out.println("Enter the Product Id: ");
    int id=readIntValue();
    boolean successDeleted=productservice.deleteProductById(id);
    if(successDeleted){
        System.out.println("product Deleted!!");
    }
    else{
        System.out.println("Product not found!!");
    }
}
public void checkIfProductExists() throws SQLException {
    System.out.println("Enter the Product Id: ");
    int id = readIntValue();
    boolean productFound=productservice.checkIfProductExists(id);
    if(productFound){
        System.out.println("Found product!!");
    }
    else{
        System.out.println("Product not found");
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
                scanner.next(); // Consume the invalid input
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
