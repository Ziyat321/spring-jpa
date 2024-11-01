package com.practice.springjpaziyat.dto;

import com.practice.springjpaziyat.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
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
}
