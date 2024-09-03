package com.example.fakestoreproductservice.service;

import com.example.fakestoreproductservice.exceptions.BadRequestException;
import com.example.fakestoreproductservice.exceptions.NotFoundException;
import com.example.fakestoreproductservice.models.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {
    Product createProduct(Product product);
    List<Product> getAllProducts(Map<String, String> queryParams);
    Product getProductById(Long id) throws NotFoundException, BadRequestException;
    Product updateProduct(Long id, Product product) throws BadRequestException;
    Product deleteProduct(Long id) throws BadRequestException;
}
