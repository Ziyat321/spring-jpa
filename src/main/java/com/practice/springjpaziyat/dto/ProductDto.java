package com.practice.springjpaziyat.dto;

import com.practice.springjpaziyat.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductDto {
    private int id;
    private String name;
    private double price;
    private String categoryName;
    private Map<String, String> values = new HashMap<>();

    public static ProductDto of(Product product, Map<String, String> values){
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getCategory().getName(),
                values
        );
    }

    public static ProductDto of(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setCategoryName(product.getCategory().getName());
        return productDto;
    }
}
