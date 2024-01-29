package com.zs.asignment10;

import com.zs.assignment10.model.Product;
import com.zs.assignment10.repository.ProductRepository;
import com.zs.assignment10.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    private ProductService productService;
    private Connection mockConnection;
    private ProductRepository mockRepository;

    @BeforeEach
    void setUp() throws SQLException {
        mockConnection = Mockito.mock(Connection.class);
        mockRepository = Mockito.mock(ProductRepository.class);
        productService = new ProductService(mockConnection, mockRepository);
    }

    @Test
    void createTable() throws SQLException {
        // Arrange
        PreparedStatement preparedStatementMock = Mockito.mock(PreparedStatement.class);
        when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(preparedStatementMock);
        // Act
        productService.createTable();
        // Assert
        Mockito.verify(preparedStatementMock).executeUpdate();
    }

    @Test
    void getAllProducts() throws SQLException {
        // Arrange
        ResultSet resultSetMock = Mockito.mock(ResultSet.class);
        when(mockRepository.getAllProduct(mockConnection)).thenReturn(resultSetMock);
        when(resultSetMock.next()).thenReturn(true, false);

        // Act
        List<Product> products = productService.getAllProducts();

        // Assert
        assertEquals(1, products.size());
    }

    @Test
    void findProductById() throws SQLException {
        // Arrange
        int productId = 1;
        ResultSet resultSetMock = Mockito.mock(ResultSet.class);
        when(mockRepository.findProductById(productId, mockConnection)).thenReturn(resultSetMock);
        when(resultSetMock.next()).thenReturn(true);
        when(resultSetMock.getInt("id")).thenReturn(productId);
        when(resultSetMock.getString("name")).thenReturn("Test Product");
        when(resultSetMock.getDouble("price")).thenReturn(50.0);

        Product foundProduct = productService.findProductById(productId);

        assertNotNull(foundProduct);
        assertEquals(productId, foundProduct.getId());
        assertEquals("Test Product", foundProduct.getName());
        assertEquals(50.0, foundProduct.getPrice(), 0.001);
    }

    @Test
    void deleteProductById() {
        int productId = 1;
        when(mockRepository.deleteProductById(productId, mockConnection)).thenReturn(true);

        boolean deleted = productService.deleteProductById(productId);

        assertTrue(deleted);
    }

    @Test
    void checkIfProductExists() throws SQLException {
        int productId = 1;
        ResultSet resultSetMock = Mockito.mock(ResultSet.class);
        when(mockRepository.checkIfProductExists(productId, mockConnection)).thenReturn(resultSetMock);
        when(resultSetMock.next()).thenReturn(true);

        boolean productExists = productService.checkIfProductExists(productId);

        assertTrue(productExists);
    }

    @Test
    void saveProduct() throws SQLException {
        String productName = "New Product";
        double productPrice = 75.0;

        ResultSet generatedKeysMock = Mockito.mock(ResultSet.class);
        when(mockRepository.insertProduct(productName, productPrice, mockConnection)).thenReturn(generatedKeysMock);
        when(generatedKeysMock.next()).thenReturn(true);
        when(generatedKeysMock.getInt(1)).thenReturn(1);

        Product savedProduct = productService.saveProduct(productName, productPrice);

        assertNotNull(savedProduct);
        assertEquals(1, savedProduct.getId());
        assertEquals(productName, savedProduct.getName());
        assertEquals(productPrice, savedProduct.getPrice());
    }
}
