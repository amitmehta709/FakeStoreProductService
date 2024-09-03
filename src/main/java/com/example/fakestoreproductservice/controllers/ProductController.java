package com.example.fakestoreproductservice.controllers;

import com.example.fakestoreproductservice.exceptions.BadRequestException;
import com.example.fakestoreproductservice.exceptions.NotFoundException;
import com.example.fakestoreproductservice.models.Product;
import com.example.fakestoreproductservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) Map<String, String> queryParams)
    {
        return new ResponseEntity<>(productService.getAllProducts(queryParams),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id)
            throws NotFoundException, BadRequestException {

        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.createProduct(product),HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product)
            throws BadRequestException {
        return new ResponseEntity<>(productService.updateProduct(id,product),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id) throws BadRequestException {
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    }
}
