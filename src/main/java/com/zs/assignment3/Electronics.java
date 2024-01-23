package com.zs.assignment3;

public class Electronics extends Product {
    private final String brand;

    public Electronics(String name, double price, boolean returnable, String brand) {
        super(name, price, returnable);
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public String toString() {
        return "name= " + getName() +
                ", price= " + getPrice() +
                ", returnable= " + isReturnable() +
                ", brand= " + brand
                ;
    }
}
