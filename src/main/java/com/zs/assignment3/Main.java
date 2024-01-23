package com.zs.assignment3;
import java.util.Scanner;

/**
 * Main class to manage the ecommerce product catalogue.
 */
public class Main {
    private static final Scanner input = new Scanner(System.in);
    private static final Catalogue catalogue = new Catalogue();

    /**
     * Main method to interact with the user and perform operations.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {

        while(true){
            System.out.println("Options: ");
            System.out.println("1. Add Categories");
            System.out.println("2. Get all Categories");
            System.out.println("3. Add product");
            System.out.println("4. Delete Product");
            System.out.println("5. Get All product");
            System.out.println("0. To Exit");
            System.out.print("Enter the option: ");

            switch(input.nextInt()){
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
                    getAllProduct();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        }
    }

    /**
     * Checks if the catalogue's category list is empty.
     * @return true if the category list is empty, false otherwise.
     */
    private static boolean categoryIsEmpty(){
        if (Main.catalogue.getCategories().isEmpty()) {
            System.out.println("No categories available. Please add a category first.");
            return true;
        }
        return false;
    }

    /**
     * Method to add a product to a specific category.
     */
    private static void addProduct() {
        while (true) {
            if (categoryIsEmpty()) {
                return;
            }

            getAllCategory();

            System.out.print("Enter the number of the category to add a product (0 to go back to menu): ");
            int categoryNumber = input.nextInt();
            if (categoryNumber == 0) {
                return;
            }
            if (categoryNumber < 1 || categoryNumber > catalogue.getCategories().size()) {
                System.out.println("Invalid category number. Please try again.");
                return;
            }
            Category category = catalogue.getCategories().get(categoryNumber - 1);
            System.out.print("Enter product name: ");
            String name = input.next();
            System.out.print("Enter product price: ");
            double price = input.nextDouble();

            boolean returnable = category.getCategoryType().isReturnable();

            switch (category.getCategoryType()) {

                case Grocery:
                    System.out.print("Enter expiry date (yyyy-mm-dd): ");
                    String expiryDate = input.next();
                    category.addProduct(new Grocery(name, price, returnable, expiryDate));
                    break;
                case Electronics:
                    System.out.print("Enter brand: ");
                    String brand = input.next();
                    category.addProduct(new Electronics(name, price, returnable, brand));
                    break;
                default:
                    category.addProduct(new Product(name, price, returnable));
                    break;
            }

            System.out.println("Product added successfully.");
        }
    }

    /**
     * Method to delete a product from a specific category.
     */
    private static void deleteProduct() {
        if (categoryIsEmpty()) {
            return;
        }

        getAllCategory();

        System.out.print("Enter the number of the category to delete a product (0 to go back to menu): ");
        int categoryNumber = input.nextInt();

        if (categoryNumber == 0) {
            return;
        }
        if (categoryNumber < 1 || categoryNumber > catalogue.getCategories().size()) {
            System.out.println("Invalid category number. Please try again.");
            return;
        }
        Category category = catalogue.getCategories().get(categoryNumber - 1);

        if (category.getProducts().isEmpty()) {
            System.out.println("No products available in this category.");
            return;
        }

        int productIndex = 1;
        System.out.println("Products in " + category.getName() + ":");
        for (Product product : category.getProducts()) {
            System.out.println(productIndex + ". " + product.getName());
            productIndex++;
        }

        System.out.print("Enter the number of the product to delete: ");
        int productNumber = input.nextInt();

        if (productNumber < 1 || productNumber > category.getProducts().size()) {
            System.out.println("Invalid product number. Please try again.");
        } else {
            Product product = category.getProducts().get(productNumber - 1);
            category.removeProduct(product);
            System.out.println("Product removed successfully.");
        }
    }


    /**
     * Method to add a new category to the catalogue.
     */
    private static void addCategory() {
        System.out.print("Enter category name (PC for PersonalCare, GR for Grocery, EL for Electronics): ");
        String categoryCode = input.next().toUpperCase();

        ProductCategory productCategory;
        switch (categoryCode) {
            case "PC":
                productCategory = ProductCategory.PersonalCare;
                break;
            case "GR":
                productCategory = ProductCategory.Grocery;
                break;
            case "EL":
                productCategory = ProductCategory.Electronics;
                break;
            default:
                productCategory = ProductCategory.Other;
                break;
        }

        catalogue.addCategory(new Category(productCategory));
        System.out.println("Category added successfully.");
    }

    /**
     * Method to display all available categories.
     */
    private static void getAllCategory() {
        if (categoryIsEmpty()) {
            return;
        }

        System.out.println("Available Categories:");
        int index = 1;
        for (Category category : catalogue.getCategories()) {
            System.out.println(index + ". " + category.getName());
            index++;
        }
        System.out.println();
    }

    /**
     * Method to display all products in all categories.
     */
    private static void getAllProduct() {
        if (catalogue.getCategories().isEmpty()) {
            System.out.println("No categories available.");
            return;
        }

        System.out.println("All Products in All Categories:");

        for (Category category : catalogue.getCategories()) {
            System.out.println("Category: " + category.getName());

            if (category.getProducts().isEmpty()) {
                System.out.println("No products available in this category.");
            } else {
                int productIndex = 1;

                for (Product product : category.getProducts()) {
                    System.out.println(productIndex + ". " + product.displayInfo());
                    productIndex++;
                }
            }

            System.out.println();
        }
    }

}