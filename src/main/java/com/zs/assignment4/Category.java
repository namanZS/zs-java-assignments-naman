package com.zs.assignment4;
import java.util.*;

public class Category {
    private final String categoryName;
    private final List<Product> products;

    Category(String categoryName){
        this.categoryName=categoryName;
        products=new ArrayList<Product>();
    }
    public void addProduct(Product product){
        this.products.add(product);

    }
    public void deleteProduct(Product product){
        products.remove(product);

    }
    public Product getProduct(String productName){
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                return product;
            }
        }
        return null;

    }
    public String getCategoryName(){
        return categoryName;
    }
    public List<Product> getAllProducts() {
        return products;
    }
}
