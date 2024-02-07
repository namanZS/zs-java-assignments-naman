package com.zs.assignment11.Controller;

import com.zs.assignment11.Model.Category;
import com.zs.assignment11.Model.Product;
import com.zs.assignment11.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@Validated
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{categoryId}")
    public List<Product> getProductsByCategory(@PathVariable Long categoryId) {
        return  categoryService.getProductsByCategory(categoryId);
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);

    }

    @PutMapping("/{categoryId}")
    public Category updateCategory(
            @PathVariable Long categoryId,
            @RequestBody Category category
    ) {
        return categoryService.updateCategory(categoryId, category);

    }
}
