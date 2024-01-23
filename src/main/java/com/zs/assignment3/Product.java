package com.zs.assignment3;

public class Product {
    private final String name;
    private final double price;
    private final boolean isReturnable;
    Product(String name,double price,boolean isReturnable){
        this.name=name;
        this.price=price;
        this.isReturnable=isReturnable;
    }

    public String getName(){
        return name;

    }

    @Override
    public String toString() {
         return "name= " + name  +
                ", price= " + price +
                ", returnable= " + isReturnable
                ;
    }

    public double getPrice() {
        return price;
    }
    public boolean isReturnable(){
        return isReturnable;
    }


}
