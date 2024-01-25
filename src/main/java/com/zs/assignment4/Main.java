package com.zs.assignment4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final Scanner input = new Scanner(System.in);
    private static final Catalogue catalogue = new Catalogue();
    private static final LruCache<String, Product> productCache = new LruCache<>(2);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Options: ");
           System.out.println("1. Add Categories");
           System.out.println("2. Get all Categories");
           System.out.println("3. Add product");
           System.out.println("4. Delete Product");
           System.out.println("5. Search product");
           System.out.println("6. Get All product");
           System.out.println("0. To Exit");
           System.out.print("Enter the option: ");

            switch (input.nextInt()) {
                case 1:
                    addCategory();
                    break;
                case 2:
                    getAllCategory();
                    break;
                case 3:
                    addProduct();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    getProduct();
                    break;
                case 6:
                    getAllProducts();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    logger.error("Invalid Input \n");
            }
        }
    }

    public static void addCategory() {
       System.out.println("Enter Category Name: ");
        String categoryName = input.next();
        catalogue.addCategory(new Category(categoryName));
       System.out.println("Category added successfully!\n ");
    }

    public static void addProduct() {
        if(catalogue.getCategories().isEmpty()){
            System.out.println("No categories available\n");
            logger.error("No categories available\n");
            return;
        }
        getAllCategory();

        System.out.println("Enter the number of the Category (0 to go back to menu): ");
        int categoryNumber = input.nextInt();
        if(categoryNumber==0)return;

        Category category = getCategoryByNumber(categoryNumber);
        if (category == null) {
            System.out.println("Invalid category number!\n");
            logger.error("Invalid category number!\n");
            return;
        }

        while (true) {
            System.out.println("Enter Product Name: ");
            String productName = input.next();

            if (category.getProduct(productName) != null) {
                System.out.println("Product with the same name already exists in the category. Please enter a different name.");
            } else {
                System.out.println("Enter Product Price: ");
                double productPrice = input.nextDouble();

                Product product = new Product(productName, productPrice);
                category.addProduct(product);
                productCache.put(productName, product);
                System.out.println("Product added successfully to category: " + category.getCategoryName());
                logger.info("Product added successfully to category: " + category.getCategoryName());
                break;
            }
        }
    }

    public static void deleteProduct() {
        if(catalogue.getCategories().isEmpty()){
            System.out.println("No categories available\n");
            logger.error("No categories available\n");
            return;
        }

        getAllCategory();

        System.out.println("Enter the number of the Category (0 to go back to menu): ");
        int categoryNumber = input.nextInt();
        if (categoryNumber == 0) {
            return;
        }

        Category category = getCategoryByNumber(categoryNumber);
        if (category == null) {
            System.out.println("Invalid category number!\n");
            logger.error("Invalid category number!\n");
            return;
        }

        System.out.println("Enter Product Name: ");
        String productName = input.next();

        Product product = category.getProduct(productName);
        if (product != null) {
            category.deleteProduct(product);
            productCache.remove(productName);
            System.out.println("Product deleted successfully from category: " + category.getCategoryName());
        } else {
            System.out.println("Product not found in category: " + category.getCategoryName());
            logger.error("Product not found in category: " + category.getCategoryName());
        }
    }

    private static void getAllCategory() {
        if(catalogue.getCategories().isEmpty()){
            System.out.println("No categories available\n");
            logger.error("No categories available\n");
            return;
        }
        System.out.println("Available Categories:");
        for (int i = 0; i < catalogue.getCategories().size(); i++) {
            System.out.println((i + 1) + ". " + catalogue.getCategories().get(i).getCategoryName());
        }
    }

    private static Category getCategoryByNumber(int categoryNumber) {
        if (categoryNumber >= 1 && categoryNumber <= catalogue.getCategories().size()) {
            return catalogue.getCategories().get(categoryNumber - 1);
        }
        return null;
    }

    public static void getProduct() {
        if(catalogue.getCategories().isEmpty()){
            System.out.println("No categories available\n");
            logger.error("No categories available\n");
            return;
        }
        System.out.println("Enter Product Name: ");
        String productName = input.next();

        Product cachedProduct = productCache.get(productName);
        if (cachedProduct != null) {
            System.out.println("Product found in cache: " + cachedProduct.getName() + ", Price: " + cachedProduct.getPrice());
        } else {
            Product product = searchProductInCategories(productName);
            if (product != null) {
                System.out.println("Product found: " + product.getName() + ", Price: " + product.getPrice());
                productCache.put(product.getName(), product);
            } else {
                System.out.println("Product not found!\n");
                logger.error("Product not found!\n");
            }
        }
    }

    private static Product searchProductInCategories(String productName) {
        for (Category category : catalogue.getCategories()) {
            Product product = category.getProduct(productName);
            if (product != null) {
                return product;
            }
        }
        return null;
    }

    public static void getAllProducts() {
        if (catalogue.getCategories().isEmpty()) {
            System.out.println("No categories found! \n");
            logger.error("No categories found! \n");
            return;
        }
        System.out.println("All Products in All Categories:");
        for (Category category : catalogue.getCategories()) {
            for (Product product : category.getAllProducts()) {
                System.out.println(product);
            }
        }
    }
}
