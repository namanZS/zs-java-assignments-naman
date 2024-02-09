package com.zs.assignment11.Service;

import com.zs.assignment11.Model.Category;
import com.zs.assignment11.Model.Product;
import com.zs.assignment11.Repository.ProductRepository;
import com.zs.assignment11.exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for handling operations related to products.
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private static final Logger logger = LogManager.getLogger(ProductService.class);

    /**
     * Constructor to initialize the ProductService with required dependencies.
     *
     * @param productRepository The repository for product data.
     * @param categoryService   The service for handling operations related to categories.
     */
    @Autowired
    public ProductService(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    /**
     * Retrieves a list of all products.
     *
     * @return List of Product objects representing all products.
     */
    public List<Product> getAllProducts() {
        logger.info("Retrieved all products");
        return productRepository.findAll();
    }

    /**
     * Creates a new product associated with the specified category.
     *
     * @param id      The ID of the category to which the product belongs.
     * @param product The product to be created.
     * @return The created Product object.
     * @throws CustomException If the specified category is not found.
     */
    public Product createProduct(long id, Product product) {
        Optional<Category> categoryOptional = categoryService.getCategory(id);
        if (categoryOptional.isEmpty()) {
            logger.error("Category not found");
            throw new CustomException("category not present", HttpStatus.NOT_FOUND);
        }
        List<Product> list = categoryOptional.get().getProducts();
        list.add(product);
        categoryOptional.get().setProducts(list);
        logger.info("Product created successfully");
        return productRepository.save(product);
    }

    /**
     * Updates an existing product with the specified ID.
     *
     * @param productId The ID of the product to be updated.
     * @param product   The updated product information.
     * @return The updated Product object.
     * @throws CustomException If the specified product is not found.
     */
    public Product updateProduct(Long productId, Product product) {
        if(product.getPrice()==0||product.getName()==null) throw new CustomException("Incomplete details of updated products",HttpStatus.BAD_REQUEST);

        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new CustomException("Product not found with ID: " + productId, HttpStatus.NOT_FOUND));
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        logger.info("Product updated successfully");
        return productRepository.save(existingProduct);
    }

    /**
     * Deletes a product with the specified ID.
     *
     * @param id The ID of the product to be deleted.
     * @throws CustomException If the specified product is not found.
     */
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) throw new CustomException("Product not found", HttpStatus.NOT_FOUND);
        else productRepository.deleteById(id);
    }
    public Product updateProductFeilds(Long id, Product updatedProduct) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new CustomException("Product not found with id: " + id,HttpStatus.NOT_FOUND));

        if (updatedProduct.getName() != null) {
            existingProduct.setName(updatedProduct.getName());
        }
        if(updatedProduct.getPrice()!=null){
            existingProduct.setPrice(updatedProduct.getPrice());
        }
        return productRepository.save(existingProduct);
    }
}
