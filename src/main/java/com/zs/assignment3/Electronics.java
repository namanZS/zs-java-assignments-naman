package com.zs.assignment3;

public class Electronics extends Product {
    private String brand;

    public Electronics(String name, double price, boolean returnable, String brand) {
        super(name, price, returnable);
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }
}
