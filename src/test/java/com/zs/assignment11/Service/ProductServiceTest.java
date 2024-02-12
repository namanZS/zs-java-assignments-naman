package com.zs.assignment11.Service;

import com.zs.assignment11.Model.Category;
import com.zs.assignment11.Model.Product;
import com.zs.assignment11.Repository.ProductRepository;
import com.zs.assignment11.Service.CategoryService;
import com.zs.assignment11.Service.ProductService;
import com.zs.assignment11.exception.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the ProductService class.
 */
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepositoryMock;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private ProductService productService;

    private Product requiredProduct, updatedProduct;
    private long productId;

    /**
     * Setup method to initialize mocks and test data.
     */
    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        productId = 1L;
        requiredProduct = new Product(productId, "Old Product", 1222.0);
        updatedProduct = new Product(productId, "Updated Product", 75.0);
    }

    /**
     * Test case for the method deleteProduct.
     */
    @Test
    void testDeleteProduct() {
        when(productRepositoryMock.existsById(productId)).thenReturn(true);
        assertDoesNotThrow(() -> productService.deleteProduct(productId));
        verify(productRepositoryMock, times(1)).existsById(productId);
        verify(productRepositoryMock, times(1)).deleteById(productId);
    }

    /**
     * Test case for the method deleteProduct when product is not found.
     */
    @Test
    void testDeleteProductNotFound() {
        when(productRepositoryMock.existsById(productId)).thenReturn(false);
        CustomException exception = assertThrows(CustomException.class, () -> productService.deleteProduct(productId));
        assertEquals("Product not found", exception.getMessage());
        verify(productRepositoryMock, times(1)).existsById(productId);
        verify(productRepositoryMock, never()).deleteById(productId);
    }

    /**
     * Test case for the method createProduct.
     */
    @Test
    public void testCreateProduct() {
        Category category = new Category(1L, "Test Category", new ArrayList<>());

        when(categoryService.getCategory(1L)).thenReturn(Optional.of(category));
        when(productRepositoryMock.save(requiredProduct)).thenReturn(requiredProduct);

        Product createdProduct = productService.createProduct(1L, requiredProduct);
        assertNotNull(createdProduct);
        assertEquals(requiredProduct.getId(), createdProduct.getId());
        assertEquals(requiredProduct.getName(), createdProduct.getName());
        assertEquals(requiredProduct.getPrice(), createdProduct.getPrice());

        verify(categoryService, times(1)).getCategory(1);
        verify(productRepositoryMock, times(1)).save(requiredProduct);
    }

    /**
     * Test case for the method createProduct when category is not found.
     */
    @Test
    void testCreateProductCategoryNotFound() {
        long categoryId = 1L;
        when(categoryService.getCategory(categoryId)).thenReturn(Optional.empty());
        CustomException exception = assertThrows(CustomException.class, () -> productService.createProduct(categoryId, requiredProduct));
        assertEquals("category not present", exception.getMessage());

        verify(categoryService, times(1)).getCategory(categoryId);
        verify(productRepositoryMock, never()).save(requiredProduct);
    }

    /**
     * Test case for the method updateProduct.
     */
    @Test
    void testUpdateProduct() {
        Long productId = 1L;
        when(productRepositoryMock.findById(productId)).thenReturn(Optional.of(requiredProduct));
        when(productRepositoryMock.save(any(Product.class))).thenReturn(updatedProduct);

        Product result = productService.updateProduct(productId, updatedProduct);
        assertNotNull(result);
        assertEquals(updatedProduct.getId(), result.getId());
        assertEquals(updatedProduct.getName(), result.getName());
        assertEquals(updatedProduct.getPrice(), result.getPrice());

        verify(productRepositoryMock, times(1)).findById(productId);
        verify(productRepositoryMock, times(1)).save(requiredProduct);
    }

    /**
     * Test case for the method updateProduct when product is not found.
     */
    @Test
    void testUpdateProductNotFound() {
        Long productId = 1L;
        Product updatedProduct = new Product(productId, "Updated Product", 75.0);

        when(productRepositoryMock.findById(productId)).thenReturn(Optional.empty());
        CustomException exception = assertThrows(CustomException.class, () -> productService.updateProduct(productId, updatedProduct));
        assertEquals("Product not found with ID: " + productId, exception.getMessage());

        verify(productRepositoryMock, times(1)).findById(productId);
        verify(productRepositoryMock, never()).save(any());
    }
    @Test
    public void testUpdateProductWithInvalidPrice() {
        Product invalidProduct = new Product(productId, "Trimmer", 0.0);
        when(productRepositoryMock.findById(productId)).thenReturn(Optional.of(new Product()));
        CustomException exception = assertThrows(CustomException.class, () -> {
            productService.updateProduct(productId, invalidProduct);
        });

        assertEquals("Incomplete details of updated products", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
    }
    @Test
    public void testUpdateProductWithInvalidName() {
        Product invalidProduct = new Product(productId, null, 1110.0);
        when(productRepositoryMock.findById(productId)).thenReturn(Optional.of(new Product()));
        CustomException exception = assertThrows(CustomException.class, () -> {
            productService.updateProduct(productId, invalidProduct);
        });

        assertEquals("Incomplete details of updated products", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
    }

    /**
     * Test case for the method getAllProducts.
     */
    @Test
    void testGetAllProducts() {
        List<Product> productList = Arrays.asList(requiredProduct, updatedProduct);

        when(productRepositoryMock.findAll()).thenReturn(productList);

        List<Product> result = productService.getAllProducts();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(requiredProduct, result.get(0));
        assertEquals(updatedProduct, result.get(1));

        verify(productRepositoryMock, times(1)).findAll();
    }
    @Test
    public void testUpdateProductFeilds() {
        Product validProduct = new Product(productId, "Updated Laptop", 12000000000.0);

        when(productRepositoryMock.findById(productId)).thenReturn(Optional.of(new Product()));
        when(productRepositoryMock.save(any(Product.class))).thenReturn(validProduct);
        Product updatedProduct = productService.updateProductFeilds(productId, validProduct);
        assertEquals(validProduct, updatedProduct);
    }
    @Test
    void testUpdateProductFeildsNotFound() {
        when(productRepositoryMock.findById(1L)).thenReturn(Optional.empty());

        CustomException exception = assertThrows(CustomException.class,
                () -> productService.updateProductFeilds(1L, updatedProduct));

        assertEquals("Product not found with id: 1", exception.getMessage());
    }

    @Test
    void testUpdateFieldsForOnlyPrice() {
        when(productRepositoryMock.findById(productId)).thenReturn(Optional.of(requiredProduct));

        Product newProduct = new Product();
        newProduct.setId(productId);
        newProduct.setPrice(10000.0);
        when(productRepositoryMock.save(any(Product.class))).thenReturn(newProduct);
        Product updatedProduct = productService.updateProductFeilds(productId, newProduct);
        Product validProduct = new Product(productId, "Laptop", 10000.0);
        assertEquals(validProduct.getPrice(), updatedProduct.getPrice());
        assertEquals(validProduct.getId(), updatedProduct.getId());


    }
    @Test
    void testUpdateFieldsForOnlyName() {
        when(productRepositoryMock.findById(productId)).thenReturn(Optional.of(requiredProduct));

        Product newProduct = new Product();
        newProduct.setId(productId);
        newProduct.setName("Lalantop");
        when(productRepositoryMock.save(any(Product.class))).thenReturn(newProduct);
        Product updatedProduct = productService.updateProductFeilds(productId, newProduct);
        Product validProduct = new Product(productId, "Lalantop", 10000.0);
        assertEquals(validProduct.getName(), updatedProduct.getName());
        assertEquals(validProduct.getId(), updatedProduct.getId());
    }



}
