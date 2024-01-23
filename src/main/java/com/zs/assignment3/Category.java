package com.zs.assignment3;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private final String name;
    private final List<Product> products;
    private final ProductCategory productCategory;

    public Category(ProductCategory productCategory) {
        this.name = productCategory.name();
        this.products = new ArrayList<>();
        this.productCategory = productCategory;
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public ProductCategory getCategoryType() {
        return productCategory;
    }
}