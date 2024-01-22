package com.zs.assignment3;

public class Grocery extends Product {
    private String expiryDate;

    public Grocery(String name, double price, boolean returnable, String expiryDate) {
        super(name, price, returnable);
        this.expiryDate = expiryDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }
}
