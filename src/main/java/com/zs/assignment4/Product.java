package com.zs.assignment4;

public class Product {
    private final String name;
    private final double price;
    Product(String name,double price){
        this.name=name;
        this.price=price;

    }
    String getName(){
        return name;
    }
    double getPrice(){
        return price;
    }

    @Override
    public String toString() {
        return "Item: "+ name+ " Price: "+ price+"\n";
    }


}
