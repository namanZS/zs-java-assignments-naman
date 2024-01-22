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
            if(categoryIsEmpty()){
                return;
            }
            else {
                getAllCategory();
            }
            System.out.print("Enter the number of the category to add the product (0 to go back to menu): ");
            int categoryNumber = input.nextInt();

            if (categoryNumber == 0) {
                return;
            }

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

                System.out.println("Product added successfully.");

                break;
            } else {
                System.out.println("Invalid category number. Please try again.");
            }
        }
    }

    /**
     * Method to delete a product from a specific category.
     */
    private static void deleteProduct() {
        while (true) {
            if(categoryIsEmpty()){
                return;
            }
            else {
                getAllCategory();
            }

            System.out.print("Enter the number of the category to delete a product (0 to go back to menu): ");
            int categoryNumber = input.nextInt();

            if (categoryNumber == 0) {
                return;
            }

            if (categoryNumber >= 1 && categoryNumber <= catalogue.getCategories().size()) {
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

                if (productNumber >= 1 && productNumber <= category.getProducts().size()) {
                    Product product = category.getProducts().get(productNumber - 1);
                    category.removeProduct(product);
                    System.out.println("Product removed successfully.");
                    break;
                } else {
                    System.out.println("Invalid product number. Please try again.");
                }
            } else {
                System.out.println("Invalid category number. Please try again.");
            }
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
        if (categoryIsEmpty()) {2

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
        if (categoryIsEmpty()) {
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
                    String productInfo;

                    if (product instanceof Grocery) {
                        productInfo = product.getName() + ", Price: " + product.getPrice() +
                                ", Expiry Date: " + ((Grocery) product).getExpiryDate();
                    } else if (product instanceof Electronics) {
                        productInfo = product.getName() + ", Price: " + product.getPrice() +
                                ", Brand: " + ((Electronics) product).getBrand();
                    } else {
                        productInfo = product.getName() + ", Price: " + product.getPrice() +
                                ", Returnable: " + product.isReturnable();
                    }

                    System.out.println(productIndex + ". " + productInfo);
                    productIndex++;
                }
            }

            System.out.println();
        }
    }
}
