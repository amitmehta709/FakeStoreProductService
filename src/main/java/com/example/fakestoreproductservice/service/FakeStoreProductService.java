package com.example.fakestoreproductservice.service;

import com.example.fakestoreproductservice.dto.FakeStoreProductDto;
import com.example.fakestoreproductservice.exceptions.BadRequestException;
import com.example.fakestoreproductservice.exceptions.NotFoundException;
import com.example.fakestoreproductservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FakeStoreProductService implements ProductService
{
    FakeStoreApiClient fakeStoreApiClient;

    public FakeStoreProductService (FakeStoreApiClient fakeStoreApiClient)
    {
        this.fakeStoreApiClient = fakeStoreApiClient;
    }

    @Override
    public Product createProduct(Product product) {
        return Product.from(fakeStoreApiClient.createProduct(FakeStoreProductDto.from(product)));
    }

    @Override
    public List<Product> getAllProducts(Map<String, String> queryParams) {
        List<FakeStoreProductDto> productDtoList = fakeStoreApiClient.getAllProducts(queryParams);
        List<Product> productList = new ArrayList<>();
        for(FakeStoreProductDto productDto : productDtoList)
        {
            productList.add(Product.from(productDto));
        }
        return productList;
    }

    @Override
    public Product getProductById(Long id) throws NotFoundException, BadRequestException {
        if(id<1)
        {
            throw new BadRequestException("Invalid Id: "+id);
        }
       return Product.from(fakeStoreApiClient.getProductById(id));
    }

    @Override
    public Product updateProduct(Long id, Product product) throws BadRequestException {
        if(id<1)
        {
            throw new BadRequestException("Invalid Id: "+id);
        }
        return Product.from(fakeStoreApiClient.updateProduct(id, FakeStoreProductDto.from(product)));
    }

    @Override
    public Product deleteProduct(Long id) throws BadRequestException {
        if(id<1)
        {
            throw new BadRequestException("Invalid Id: "+id);
        }
        return Product.from(fakeStoreApiClient.deleteProduct(id));
    }

}
