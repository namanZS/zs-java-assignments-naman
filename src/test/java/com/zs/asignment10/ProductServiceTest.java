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

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProductServiceTest {

    @Mock
    private ProductRepository mockRepository;
    @Mock
    private Connection mockConnection;
    @InjectMocks
    private ProductService productService;
    @BeforeEach
    void setUp() {
        mockConnection = Mockito.mock(Connection.class);
        mockRepository = Mockito.mock(ProductRepository.class);
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void testGetAllProducts() throws SQLException {
        // Arrange
        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(new Product(1, "Test Product", 50.0));

        when(mockRepository.getAllProduct(mockConnection)).thenReturn(expectedProducts);

        List<Product> actualProducts = productService.getAllProducts();

        assertEquals(expectedProducts, actualProducts);
    }

    @Test
    void testFindProductById() throws SQLException {
        // Arrange
        int productId = 1;
        Product expectedProduct = new Product(productId, "Test Product", 50.0);

        when(mockRepository.findProductById(Mockito.eq(productId), Mockito.any(Connection.class))).thenReturn(expectedProduct);

        Product actualProduct = productService.findProductById(productId);

        assertEquals(expectedProduct, actualProduct);
    }

    @Test
    void testDeleteProductById() throws SQLException {
        // Arrange
        int productId = 1;
        when(mockRepository.deleteProductById(Mockito.eq(productId), Mockito.any(Connection.class))).thenReturn(true);

        boolean result = productService.deleteProductById(productId);

        assertTrue(result);
    }

    @Test
    void testCheckIfProductExists() throws SQLException {
        int productId = 1;
        when(mockRepository.checkIfProductExists(Mockito.eq(productId), Mockito.any(Connection.class))).thenReturn(true);

        boolean result = productService.checkIfProductExists(productId);

        assertTrue(result);
    }

    @Test
    void testSaveProduct() throws SQLException {
        String productName = "New Product";
        double productPrice = 75.0;
        Product expectedProduct = new Product(1, productName, productPrice);

        when(mockRepository.insertProduct(Mockito.eq(productName), Mockito.eq(productPrice), Mockito.any(Connection.class)))
                .thenReturn(expectedProduct);

        Product actualProduct = productService.saveProduct(productName, productPrice);

        assertNotNull(actualProduct);
        assertEquals(expectedProduct, actualProduct);
    }
}
