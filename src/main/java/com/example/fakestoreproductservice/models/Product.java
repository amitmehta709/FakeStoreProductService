package com.example.fakestoreproductservice.models;

import com.example.fakestoreproductservice.dto.FakeStoreProductDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel {
    private String name;
    private double price;
    private Category category;
    private String description;

    public static Product from(FakeStoreProductDto fakeProductDto) {
        if(fakeProductDto==null)
        {
            return null;
        }
        Product product = new Product();
        product.setId(fakeProductDto.getId());
        product.setName(fakeProductDto.getTitle());
        product.setPrice(fakeProductDto.getPrice());
        product.setDescription(fakeProductDto.getDescription());
        Category category = new Category();
        category.setId(1L);
        category.setTitle(fakeProductDto.getCategory());
        product.setCategory(category);
        return product;
    }
}
