package com.zs.assignment11.Controller;

import com.zs.assignment11.Model.Category;
import com.zs.assignment11.Model.Product;
import com.zs.assignment11.Service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling Category-related endpoints.
 */
@RestController
@RequestMapping("/category")
@Validated
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * Endpoint to retrieve all categories.
     *
     * @return List of Category objects representing all categories.
     */
    @GetMapping
    @Operation(
            summary = "Get all categories"
    )
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    /**
     * Endpoint to retrieve products by category ID.
     *
     * @param categoryId The ID of the category for which products are to be retrieved.
     * @return List of Product objects associated with the specified category.
     */
    @Operation(
            summary = "Get All products for a particular category"
    )
    @GetMapping("/{categoryId}/products")
    public Category getProductsByCategory(@PathVariable Long categoryId) {
        return categoryService.getProductsByCategory(categoryId);
    }
    /**
     * Endpoint to create a new category.
     *
     * @param category The Category object representing the new category to be created.
     * @return The created Category object.
     */
    @Operation(
            summary = "Create a category"
    )
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }
}
