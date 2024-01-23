package com.zs.assignment3;
import java.util.ArrayList;
import java.util.List;

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
}