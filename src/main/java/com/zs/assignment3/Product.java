package com.zs.assignment3;

public class Product {
    private String name;
    private double price;
    private boolean isReturnable;
    Product(String name,double price,boolean isReturnable){
        this.name=name;
        this.price=price;
        this.isReturnable=isReturnable;
    }

    public String getName(){
        return name;

    }
    public double getPrice() {
        return price;
    }
    public boolean isReturnable(){
        return isReturnable;
    }


}
