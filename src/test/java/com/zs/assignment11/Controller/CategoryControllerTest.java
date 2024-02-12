package com.zs.assignment11.Controller;

import com.zs.assignment11.Model.Category;
import com.zs.assignment11.Model.Product;
import com.zs.assignment11.Service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit tests for the CategoryController class.
 */
public class CategoryControllerTest {

    private MockMvc mockMvc;
    @Mock
    private CategoryService categoryService;
    @InjectMocks
    private CategoryController categoryController;
    private List<Category> mockCategories;
    private List<Product> mockProducts;

    /**
     * Setup method to initialize mocks and create the MockMvc instance.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
        mockCategories = new ArrayList<>();
        mockProducts = new ArrayList<>();
        mockCategories.add(new Category(1L, "Electronics"));
        mockCategories.add(new Category(2L, "Clothing"));
        mockProducts.add(new Product(1L, "Laptop", 1000.0));
        mockCategories.get(0).setProducts(mockProducts);
    }

    /**
     * Test case for retrieving all categories.
     *
     * @throws Exception if an error occurs during the test execution
     */
    @Test
    public void testGetAllCategories() throws Exception {
        when(categoryService.getAllCategories()).thenReturn(mockCategories);

        mockMvc.perform(get("/category")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Electronics"));
    }

    /**
     * Test case for retrieving products by category ID.
     *
     * @throws Exception if an error occurs during the test execution
     */
    @Test
    public void testGetProductsByCategory() throws Exception {
        Category mockCategory = mockCategories.get(0);

        // Stubbing the categoryService.getProductsByCategory(1L) to return the mockCategory
        when(categoryService.getProductsByCategory(1L)).thenReturn(mockCategory);

        mockMvc.perform(get("/category/1/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.products[0].id").value(mockCategory.getProducts().get(0).getId()))
                .andExpect(jsonPath("$.products[0].name").value(mockCategory.getProducts().get(0).getName()))
                .andExpect(jsonPath("$.products[0].price").value(mockCategory.getProducts().get(0).getPrice()));
    }

    /**
     * Test case for creating a new category.
     *
     * @throws Exception if an error occurs during the test execution
     */
    @Test
    public void testCreateCategory() throws Exception {
        when(categoryService.createCategory(any(Category.class))).thenReturn(mockCategories.get(1));

        mockMvc.perform(post("/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Clothing\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("Clothing"));
    }
}
