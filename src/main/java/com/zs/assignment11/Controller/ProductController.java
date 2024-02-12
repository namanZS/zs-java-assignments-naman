package com.zs.assignment11.Controller;

import com.zs.assignment11.Model.Product;
import com.zs.assignment11.Service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling Product-related endpoints.
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Endpoint to retrieve all products.
     *
     * @return List of Product objects representing all products.
     */
    @Operation(
            summary = "Get all products"
    )
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    /**
     * Endpoint to add a new product to a specific category.
     *
     * @param catId   The ID of the category to which the product belongs.
     * @param product The Product object representing the new product to be added.
     * @return The created Product object.
     */

    @Operation(
            summary = "Create a product"
    )
    @PostMapping("/{catId}")
    public Product addProduct(@PathVariable("catId") long catId, @RequestBody Product product) {
        return productService.createProduct(catId, product);
    }

    /**
     * Endpoint to update an existing product.
     *
     * @param id      The ID of the product to be updated.
     * @param product The updated Product object.
     * @return The updated Product object.
     */

    @Operation(
            summary = "Update complete details fo a product"
    )
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    /**
     * Endpoint to delete a product by ID.
     *
     * @param id The ID of the product to be deleted.
     */
    @Operation(
            summary = "Delete a product"
    )
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }
    @Operation(
            summary = "Update some fields of a product"
    )
    @PatchMapping("/{id}")
    public Product updateProductpartially(@PathVariable("id")Long id,@RequestBody Product product){
        return productService.updateProductFeilds(id,product);
    }



}
