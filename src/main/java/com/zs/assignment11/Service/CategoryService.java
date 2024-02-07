package com.zs.assignment11.Service;

import com.zs.assignment11.Model.Category;
import com.zs.assignment11.Model.Product;
import com.zs.assignment11.Repository.CategoryRepository;
import com.zs.assignment11.exception.CustomException;
import com.zs.assignment9.Repository.BuildConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private  final Logger logger;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        logger = LogManager.getLogger(CategoryService.class);
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public List<Product> getProductsByCategory(Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isEmpty()) {
            logger.error("Category id not found!!");
            throw new CustomException("Category not found with ID: " + categoryId);
        }
        logger.info("Retrieved products for category with ID", categoryId);
        return category.get().getProducts();
    }

    public Category createCategory(Category category) {
        if (category.getName() == null || category.getProducts() == null) {
            logger.error("Incomplete category details");
            throw new CustomException("Enter complete details of category");
        }
        logger.info("Category Created");
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long categoryId, Category category) {

        Optional<Category> existingCategory = Optional.ofNullable(categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CustomException("Category not found with ID: " + categoryId)));
        if (category.getName() == null) {
            throw new IllegalArgumentException("Category name cannot be null");
        }
        existingCategory.get().setName(category.getName());
        logger.info("Category updated successfully");
        return categoryRepository.save(existingCategory.get());
    }

    public Optional<Category> getCategory(long id) {
        if (categoryRepository.findById(id).isEmpty()) {
            logger.error("category not found");
            throw new CustomException("Category id not found");
        }
        logger.info("Category fetched successfully!");
        return categoryRepository.findById(id);
    }
}
