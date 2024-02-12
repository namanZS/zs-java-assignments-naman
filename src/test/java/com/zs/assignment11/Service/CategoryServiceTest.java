package com.zs.assignment11.Service;

import com.zs.assignment11.Model.Category;
import com.zs.assignment11.Model.Product;
import com.zs.assignment11.Repository.CategoryRepository;
import com.zs.assignment11.Repository.ProductRepository;
import com.zs.assignment11.Service.CategoryService;
import com.zs.assignment11.exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CategoryService categoryService;

    private Long categoryId;
    private List<Product> products;
    private Logger logger;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        categoryId = 1L;
        products = new ArrayList<>();
        products.add(new Product(1L, "Product 1", 500000000.0));
        products.add(new Product(2L, "Product 2", 750000000.0));
        logger = LogManager.getLogger(CategoryService.class);
    }

    /**
     * Test case for the method getProductsByCategory.
     */
    @Test
    void testGetProductsByCategory() {
        Category category = new Category(categoryId, "Category 1", products);
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        Category result = categoryService.getProductsByCategory(categoryId);
        assertNotNull(result);
        assertEquals(products.size(), result.getProducts().size());
        assertEquals(products, result.getProducts());

        verify(categoryRepository, times(1)).findById(categoryId);
    }

    /**
     * Test case for the method getProductsByCategory when category is not found.
     */
    @Test
    void testGetProductsByCategoryNotFound() {
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());
        CustomException exception = assertThrows(CustomException.class, () -> categoryService.getProductsByCategory(categoryId));
        assertEquals("Category not found with ID: " + categoryId, exception.getMessage());

        verify(categoryRepository, times(1)).findById(categoryId);
    }

    /**
     * Test case for the method createCategory.
     */
    @Test
    void testCreateCategory() {
        Category categoryToCreate = new Category("New Category");
        when(categoryRepository.save(categoryToCreate)).thenReturn(categoryToCreate);

        Category createdCategory = categoryService.createCategory(categoryToCreate);
        assertNotNull(createdCategory);
        assertEquals(categoryToCreate.getName(), createdCategory.getName());
        verify(categoryRepository, times(1)).save(categoryToCreate);
    }

    /**
     * Test case for the method createCategory with incomplete details.
     */
    @Test
    void testCreateCategoryIncompleteDetails() {
        Category incompleteCategory = new Category(); // Name and Products are null

        CustomException exception = assertThrows(CustomException.class, () -> categoryService.createCategory(incompleteCategory));
        assertEquals("Enter complete details of category", exception.getMessage());

        verify(categoryRepository, never()).save(any());
    }

    /**
     * Test case for the method getAllCategories.
     */
    @Test
    void testGetAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1L, "Category 1", new ArrayList<>()));
        categoryList.add(new Category(2L, "Category 2", new ArrayList<>()));
        when(categoryRepository.findAll()).thenReturn(categoryList);

        List<Category> result = categoryService.getAllCategories();

        assertNotNull(result);
        assertEquals(categoryList.size(), result.size());
        assertEquals(categoryList, result);

        verify(categoryRepository, times(1)).findAll();
    }

    /**
     * Test case for the method getCategory when category is not found.
     */
    @Test
    void testGetCategoryNotFound() {
        long nonExistentCategoryId = 100L;
        when(categoryRepository.findById(nonExistentCategoryId)).thenReturn(Optional.empty());
        CustomException exception = assertThrows(CustomException.class, () -> categoryService.getCategory(nonExistentCategoryId));
        assertEquals("Category id not found", exception.getMessage());
        verify(categoryRepository, times(1)).findById(nonExistentCategoryId);
    }

    /**
     * Test case for the method getCategory.
     */
    @Test
    void testGetCategory() {
        Category mockCategory = new Category(categoryId, "Test Category", new ArrayList<>());
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(mockCategory));
        Optional<Category> result = categoryService.getCategory(categoryId);
        assertTrue(result.isPresent());
        assertEquals(mockCategory, result.get());
        verify(categoryRepository, times(2)).findById(categoryId);
    }

}
