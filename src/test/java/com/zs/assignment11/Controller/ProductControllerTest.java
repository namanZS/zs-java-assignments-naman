package com.zs.assignment11.Controller;

import com.zs.assignment11.Model.Category;
import com.zs.assignment11.Model.Product;
import com.zs.assignment11.Service.ProductService;
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

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit tests for the ProductController class.
 */
public class ProductControllerTest {

    private MockMvc mockMvc;
    @Mock
    private ProductService productService;
    @InjectMocks
    private ProductController productController;
    private List<Category> mockCategories;
    private List<Product> mockProducts;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        mockProducts = new ArrayList<>();
        mockProducts.add(new Product(1L, "sansoong", 1000000.0));
    }

    /**
     * Test case for retrieving all products.
     *
     * @throws Exception if an error occurs during the test execution
     */
    @Test
    public void testGetAllProducts() throws Exception {
        when(productService.getAllProducts()).thenReturn(mockProducts);

        mockMvc.perform(get("/product")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("sansoong"))
                .andExpect(jsonPath("$[0].price").value(1000000.0));

    }

    /**
     * Test case for adding a new product.
     *
     * @throws Exception if an error occurs during the test execution
     */
    @Test
    public void testAddProducts() throws Exception {

        when(productService.createProduct(anyLong(), any(Product.class))).thenReturn(mockProducts.get(0));

        mockMvc.perform(post("/product/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"sansoong\",\"price\":1000000.0}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("sansoong"))
                .andExpect(jsonPath("$.price").value(1000000.0));
    }

    /**
     * Test case for updating a product.
     *
     * @throws Exception if an error occurs during the test execution
     */
    @Test
    public void testUpdateProduct() throws Exception {
        Product updatedProduct = new Product(1L, "samsung", 1000.0);
        when(productService.updateProduct(anyLong(), any(Product.class))).thenReturn(updatedProduct);

        mockMvc.perform(put("/product/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"samsung\",\"price\":1000.0}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(updatedProduct.getId()))
                .andExpect(jsonPath("$.name").value(updatedProduct.getName()))
                .andExpect(jsonPath("$.price").value(updatedProduct.getPrice()));
    }

    /**
     * Test case for deleting a product.
     *
     * @throws Exception if an error occurs during the test execution
     */
    @Test
    public void testDeleteProduct() throws Exception {
        mockMvc.perform(delete("/product/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"samsung\",\"price\":1000.0}"))
                .andExpect(status().isOk());
        verify(productService, times(1)).deleteProduct(1L);
    }
    @Test
    public void testUpdateDetails() throws Exception{
        Product updatedProduct = new Product(1L,"samsung", 100000.0);

        when(productService.updateProductFeilds(anyLong(), any(Product.class))).thenReturn(new Product(1L,"samsung",10000.0));

        mockMvc.perform(patch("/product/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"price\":100000.0}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
