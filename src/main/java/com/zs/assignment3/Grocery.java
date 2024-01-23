package com.zs.assignment3;

public class Grocery extends Product {
    private final String expiryDate;

    public Grocery(String name, double price, boolean returnable, String expiryDate) {
        super(name, price, returnable);
        this.expiryDate = expiryDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    @Override
    public String toString() {
        return "name= " + getName() +
                ", price= " + getPrice() +
                ", returnable= " + isReturnable() +
                ", expiryDate= " + expiryDate
                ;
    }
}
