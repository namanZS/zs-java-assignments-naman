package com.zs.assignment11.Service;

import com.zs.assignment11.Model.Category;
import com.zs.assignment11.Model.Product;
import com.zs.assignment11.Repository.ProductRepository;
import com.zs.assignment11.exception.CustomException;
import com.zs.assignment9.Repository.BuildConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private static final Logger logger = LogManager.getLogger(ProductService.class);
    @Autowired
    public ProductService(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }
    public List<Product> getAllProducts() {
        logger.info("Retrieved all products");
        return productRepository.findAll();
    }
    public Product createProduct(long id,Product product) {
        Optional<Category> categoryOptional = categoryService.getCategory(id);
        if (categoryOptional.isEmpty()){
            logger.error("Category not found");
            throw new CustomException("category not present");
        }
        List<Product> list = categoryOptional.get().getProducts();
        list.add(product);
        categoryOptional.get().setProducts(list);
        logger.info("product created successfully");
        return productRepository.save(product);
    }
    public Product updateProduct(Long productId, Product product) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new CustomException("Product not found with ID: " + productId));
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        logger.info("Product updated successfully");
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        if(!productRepository.existsById(id))throw new CustomException("Product not found");
        else productRepository.deleteById(id);
    }

}
