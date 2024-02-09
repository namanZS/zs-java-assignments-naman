package com.zs.assignment11.Service;

import com.zs.assignment11.Model.Category;
import com.zs.assignment11.Model.Product;
import com.zs.assignment11.Repository.CategoryRepository;
import com.zs.assignment11.exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for handling Category-related operations.
 */
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final Logger logger;

    /**
     * Constructor for CategoryService.
     *
     * @param categoryRepository The repository for Category entities.
     */
    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        logger = LogManager.getLogger(CategoryService.class);
        this.categoryRepository = categoryRepository;
    }

    /**
     * Retrieves all categories.
     *
     * @return List of Category objects representing all categories.
     */
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    /**
     * Retrieves products by category ID.
     *
     * @param categoryId The ID of the category for which products are to be retrieved.
     * @return List of Product objects associated with the specified category.
     * @throws CustomException if the category is not found.
     */
    public List<Product> getProductsByCategory(Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isEmpty()) {
            logger.error("Category id not found!!");
            throw new CustomException("Category not found with ID: " + categoryId);
        }
        logger.info("Retrieved products for category with ID", categoryId);
        return category.get().getProducts();
    }

    /**
     * Creates a new category.
     *
     * @param category The Category object representing the new category to be created.
     * @return The created Category object.
     * @throws CustomException if category details are incomplete.
     */
    public Category createCategory(Category category) {
        if (category.getName() == null || category.getProducts() == null) {
            logger.error("Incomplete category details");
            throw new CustomException("Enter complete details of category");
        }
        logger.info("Category Created");
        return categoryRepository.save(category);
    }

    /**
     * Retrieves a category by ID.
     *
     * @param id The ID of the category to be retrieved.
     * @return Optional containing the Category object if found.
     * @throws CustomException if the category is not found.
     */
    public Optional<Category> getCategory(long id) {
        if (categoryRepository.findById(id).isEmpty()) {
            logger.error("category not found");
            throw new CustomException("Category id not found");
        }
        logger.info("Category fetched successfully!");
        return categoryRepository.findById(id);
    }
}
