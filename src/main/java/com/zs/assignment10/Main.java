package com.zs.assignment10;

import com.zs.assignment10.controller.ProductController;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String []args) throws SQLException {
        ProductController controller =new ProductController();
        while(true){
            System.out.println("1. Find All products");
            System.out.println("2. Find product by ID");
            System.out.println("3. Save product");
            System.out.println("4. Delete product by ID");
            System.out.println("5. Check if product Exists");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            switch (scanner.nextInt()) {
                case 1:
                    controller.findAllProducts();
                    break;
                case 2:
                    controller.findProductById();
                    break;
                case 3:
                    controller.saveProduct();
                    break;
                case 4:
                    controller.deleteProductById();
                    break;
                case 5:
                    controller.checkIfProductExists();
                    break;
                case 0:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

    }
}
