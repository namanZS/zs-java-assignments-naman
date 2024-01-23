package com.zs.assignment4;

import java.util.*;

public class Catalogue {
    private final List<Category> categories;

    public Catalogue() {
        this.categories = new ArrayList<>();
    }
    public List<Category> getCategories() {
        return categories;
    }
    public void addCategory(Category category) {
        categories.add(category);
    }


    @Override
    public String toString() {
        return "categories=" + categories;
    }
}
