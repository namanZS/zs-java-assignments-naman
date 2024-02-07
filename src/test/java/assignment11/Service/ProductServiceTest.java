package assignment11.Service;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ProductServiceTest {
    @Mock
    private ProductRepository productRepositoryMock;
    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private ProductService productService;
    private Product requiredProduct,updatedProduct;
    private long productId;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        productId=1L;
        requiredProduct=new Product(productId,"Old Product",1222.0);
        updatedProduct = new Product(productId, "Updated Product", 75.0);

    }

    /**
     * Test cases for success
     */
    @Test
    void testDeleteProduct() {

        when(productRepositoryMock.existsById(productId)).thenReturn(true);
        assertDoesNotThrow(() -> productService.deleteProduct(productId));
        verify(productRepositoryMock, times(1)).existsById(productId);
        verify(productRepositoryMock, times(1)).deleteById(productId);
    }

    @Test
    void testDeleteProductNotFound() {

        when(productRepositoryMock.existsById(productId)).thenReturn(false);
        CustomException exception = assertThrows(CustomException.class, () -> productService.deleteProduct(productId));
        assertEquals("Product not found", exception.getMessage());
        verify(productRepositoryMock, times(1)).existsById(productId);
        verify(productRepositoryMock, never()).deleteById(productId);
    }

    @Test
    public void testCreateProduct(){
        Category category = new Category(1L, "Test Category", new ArrayList<>());

        when(categoryService.getCategory(1L)).thenReturn(Optional.of(category));
        when(productRepositoryMock.save(requiredProduct)).thenReturn(requiredProduct);

        // Act
        Product createdProduct = productService.createProduct(1L, requiredProduct);

        // Assert
        assertNotNull(createdProduct);
        assertEquals(requiredProduct.getId(), createdProduct.getId());
        assertEquals(requiredProduct.getName(), createdProduct.getName());
        assertEquals(requiredProduct.getPrice(), createdProduct.getPrice());

        verify(categoryService, times(1)).getCategory(1);
        verify(productRepositoryMock, times(1)).save(requiredProduct);

    }
    @Test
    void testCreateProductCategoryNotFound() {

        long categoryId = 1L;
        when(categoryService.getCategory(categoryId)).thenReturn(Optional.empty());
        CustomException exception = assertThrows(CustomException.class,()->productService.createProduct(categoryId, requiredProduct));
        assertEquals("category not present", exception.getMessage());

        verify(categoryService, times(1)).getCategory(categoryId);
        verify(productRepositoryMock, never()).save(requiredProduct);
    }
    @Test
    void testUpdateProduct() {
        // Arrange
        Long productId = 1L;
        when(productRepositoryMock.findById(productId)).thenReturn(Optional.of(requiredProduct));
        when(productRepositoryMock.save(any(Product.class))).thenReturn(updatedProduct);

        // Act
        Product result = productService.updateProduct(productId, updatedProduct);

        // Assert
        assertNotNull(result);
        assertEquals(updatedProduct.getId(), result.getId());
        assertEquals(updatedProduct.getName(), result.getName());
        assertEquals(updatedProduct.getPrice(), result.getPrice());

        verify(productRepositoryMock, times(1)).findById(productId);
        verify(productRepositoryMock, times(1)).save(requiredProduct);
    }

    @Test
    void testUpdateProductNotFound() {
        Long productId = 1L;
        Product updatedProduct = new Product(productId, "Updated Product", 75.0);

        when(productRepositoryMock.findById(productId)).thenReturn(Optional.empty());

        // Act and Assert
        CustomException exception = assertThrows(CustomException.class, () -> productService.updateProduct(productId, updatedProduct));
        assertEquals("Product not found with ID: " + productId, exception.getMessage());

        verify(productRepositoryMock, times(1)).findById(productId);
        verify(productRepositoryMock, never()).save(any());
    }
    @Test
    void testGetAllProducts() {

        List<Product> productList = Arrays.asList(requiredProduct, updatedProduct);

        when(productRepositoryMock.findAll()).thenReturn(productList);

        // Act
        List<Product> result = productService.getAllProducts();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(requiredProduct, result.get(0));
        assertEquals(updatedProduct, result.get(1));

        verify(productRepositoryMock, times(1)).findAll();
    }



}
