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
     * Method to add a product to a specific category.
     */
    private static void addProduct() {
        getAllCategory();

        System.out.print("Enter the number of the category to add the product: ");
        int categoryNumber = input.nextInt();


        if (categoryNumber >= 1 && categoryNumber <= catalogue.getCategories().size()) {
            Category category = catalogue.getCategories().get(categoryNumber - 1);
            System.out.print("Enter product name: ");
            String name = input.next();
            System.out.print("Enter product price: ");
            double price = input.nextDouble();

            boolean returnable = true;

            if (category.getName().equalsIgnoreCase("PersonalCare")) {
                returnable = false;
            } else if (category.getName().equalsIgnoreCase("Grocery")) {
                System.out.print("Enter expiry date (yyyy-mm-dd): ");
                String expiryDate = input.next();
                category.addProduct(new Grocery(name, price, returnable, expiryDate));
            } else if (category.getName().equalsIgnoreCase("Electronics")) {
                System.out.print("Enter brand: ");
                String brand = input.next();
                category.addProduct(new Electronics(name, price, returnable, brand));
            } else {
                category.addProduct(new Product(name, price, returnable));
            }
        } else {
            System.out.println("Invalid category number.");
        }
    }


    /**
     * Method to add a new category to the catalogue.
     */
    private static void addCategory() {
        System.out.print("Enter category name: ");
        String categoryName = input.next();
        catalogue.addCategory(new Category(categoryName));
        System.out.println("Category added successfully.");
    }

    /**
     * Method to display all available categories.
     */
    private static void getAllCategory() {
        System.out.println("Available Categories:");
        int index = 1;
        for (Category category : catalogue.getCategories()) {
            System.out.println(index + ". " + category.getName());
            index++;
        }
        System.out.println();
    }

    /**
     * Method to delete a product from a specific category.
     */
    private static void deleteProduct() {
        getAllCategory();

        System.out.print("Enter the number of the category to delete a product: ");
        int categoryNumber = input.nextInt();

        if (categoryNumber >= 1 && categoryNumber <= catalogue.getCategories().size()) {
            Category category = catalogue.getCategories().get(categoryNumber - 1);

            int productIndex = 1;
            System.out.println("Products in " + category.getName() + ":");
            for (Product product : category.getProducts()) {
                System.out.println(productIndex + ". " + product.getName());
                productIndex++;
            }

            System.out.print("Enter the number of the product to delete: ");
            int productNumber = input.nextInt();

            if (productNumber >= 1 && productNumber <= category.getProducts().size()) {
                Product product = category.getProducts().get(productNumber - 1);
                category.removeProduct(product);
                System.out.println("Product removed successfully.");
            } else {
                System.out.println("Invalid product number.");
            }
        } else {
            System.out.println("Invalid category number.");
        }
    }

    /**
     * Method to display all products in all categories.
     */
    private static void getAllProduct() {
        System.out.println("All Products in All Categories:");

        // Iterate through all categories
        for (Category category : catalogue.getCategories()) {
            System.out.println("Category: " + category.getName());
            int productIndex = 1;
            for (Product product : category.getProducts()) {
                String productInfo;

                if (product instanceof Grocery) {
                    productInfo = "  Grocery: " + product.getName() + ", Price: " + product.getPrice() +
                            ", Expiry Date: " + ((Grocery) product).getExpiryDate();
                } else if (product instanceof Electronics) {
                    productInfo = "  Electronics: " + product.getName() + ", Price: " + product.getPrice() +
                            ", Brand: " + ((Electronics) product).getBrand();
                } else {
                    productInfo = "  Product: " + product.getName() + ", Price: " + product.getPrice() +
                            ", Returnable: " + product.isReturnable();
                }

                System.out.println(productIndex + ". " + productInfo);
                productIndex++;
            }
            System.out.println();
        }
    }



}
