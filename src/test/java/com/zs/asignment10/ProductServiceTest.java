package com.zs.asignment10;

import com.zs.assignment10.model.Product;
import com.zs.assignment10.repository.ProductRepository;
import com.zs.assignment10.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    @Mock
    private ProductRepository mockRepository;
    @Mock
    private Connection mockConnection;
    @InjectMocks
    private ProductService productService;
    private Product expectedProduct;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        expectedProduct = new Product(1, "Test Product", 75.0);
    }
    @Test
    void testProductServiceConstructor_SQLException() throws SQLException, IOException {

        assertThrows(SQLException.class, () -> {
            productService = new ProductService();
        });
    }

    /**
     * Test case: Get all products successfully.
     */
    @Test
    void testGetAllProducts_Success() throws SQLException {
        // Arrange
        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(new Product(1, "Test Product", 50.0));
        when(mockRepository.getAllProduct(mockConnection)).thenReturn(expectedProducts);

        // Act
        List<Product> actualProducts = productService.getAllProducts();

        // Assert
        assertNotNull(actualProducts);
        assertEquals(expectedProducts, actualProducts);
    }

    /**
     * Test case: Get all products fails.
     */
    @Test
    void testGetAllProducts_Fail() throws SQLException {
        // Arrange
        when(mockRepository.getAllProduct(mockConnection)).thenThrow(new SQLException("Database connection error"));

        // Act and Assert
        assertThrows(SQLException.class, () -> productService.getAllProducts());
    }

    /**
     * Test case: Find product by id successfully.
     */
    @Test
    void testFindProductById_Success() throws SQLException {
        // Arrange
        when(mockRepository.findProductById(Mockito.eq(expectedProduct.getId()), Mockito.any(Connection.class))).thenReturn(expectedProduct);

        // Act
        Product actualProduct = productService.findProductById(expectedProduct.getId());

        // Assert
        assertNotNull(actualProduct);
        assertEquals(expectedProduct.getId(), actualProduct.getId());
        assertEquals(expectedProduct.getName(), actualProduct.getName());
        assertEquals(expectedProduct.getPrice(), actualProduct.getPrice());
    }

    /**
     * Test case: Find product by id fails.
     */
    @Test
    void testFindProductById_Fail() throws SQLException {
        // Arrange
        when(mockRepository.findProductById(Mockito.eq(expectedProduct.getId()), Mockito.any(Connection.class))).thenThrow(new SQLException());
        // Act and Assert
        assertThrows(SQLException.class, () -> productService.findProductById(expectedProduct.getId()));
    }

    /**
     * Test case: Delete product by id successfully.
     */
    @Test
    void testDeleteProductById_Success() throws SQLException {
        // Arrange
        when(mockRepository.deleteProductById(Mockito.eq(expectedProduct.getId()), Mockito.any(Connection.class))).thenReturn(true);

        // Act
        boolean result = productService.deleteProductById(expectedProduct.getId());

        // Assert
        assertTrue(result);
    }

    /**
     * Test case: Delete product by id fails.
     */
    @Test
    void testDeleteProductById_Fail() throws SQLException {
        // Arrange
        when(mockRepository.deleteProductById(Mockito.eq(expectedProduct.getId()), Mockito.any(Connection.class))).thenReturn(false);

        // Act and Assert
        assertFalse(productService.deleteProductById(expectedProduct.getId()));
    }

    /**
     * Test case: Check if product exists successfully.
     */
    @Test
    void testCheckIfProductExists_Success() throws SQLException {
        // Arrange
        when(mockRepository.checkIfProductExists(Mockito.eq(expectedProduct.getId()), Mockito.any(Connection.class))).thenReturn(true);

        // Act
        boolean result = productService.checkIfProductExists(expectedProduct.getId());

        // Assert
        assertTrue(result);
    }

    /**
     * Test case: Check if product exists fails.
     */
    @Test
    void testCheckIfProductExists_Fail() throws SQLException {
        // Arrange
        when(mockRepository.checkIfProductExists(Mockito.eq(expectedProduct.getId()), Mockito.any(Connection.class))).thenReturn(false);

        // Act and Assert
        assertFalse(productService.checkIfProductExists(expectedProduct.getId()));
    }

    /**
     * Test case: Save product successfully.
     */
    @Test
    void testSaveProduct_Success() throws SQLException {
        // Arrange
        when(mockRepository.insertProduct(Mockito.eq(expectedProduct.getName()), Mockito.eq(expectedProduct.getPrice()), Mockito.any(Connection.class)))
                .thenReturn(expectedProduct);

        // Act
        Product actualProduct = productService.saveProduct(expectedProduct.getName(), expectedProduct.getPrice());

        // Assert
        assertNotNull(actualProduct);
        assertEquals(expectedProduct.getId(), actualProduct.getId());
        assertEquals(expectedProduct.getName(), actualProduct.getName());
        assertEquals(expectedProduct.getPrice(), actualProduct.getPrice());
    }

    /**
     * Test case: Save product fails.
     */
    @Test
    void testSaveProduct_Fail() throws SQLException {
        when(mockRepository.insertProduct(Mockito.eq(expectedProduct.getName()), Mockito.eq(expectedProduct.getPrice()), Mockito.any(Connection.class)))
                .thenThrow(new SQLException("Database connection error"));

        assertThrows(SQLException.class, () -> productService.saveProduct(expectedProduct.getName(), expectedProduct.getPrice()));
    }
}
