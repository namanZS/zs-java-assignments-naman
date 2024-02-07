package com.zs.assignment11.Controller;
import com.zs.assignment11.Model.Product;
import com.zs.assignment11.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
    @PostMapping("/{catId}")
    public Product addProduct(@PathVariable("catId") long catId, @RequestBody Product product){
         return productService.createProduct(catId,product);
    }
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id")Long id,@RequestBody Product product){
        return productService.updateProduct(id,product);
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
    }
}
