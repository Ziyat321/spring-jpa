package com.practice.springjpaziyat.service;

import com.practice.springjpaziyat.model.Category;
import com.practice.springjpaziyat.model.Product;
import com.practice.springjpaziyat.repository.CategoryRepository;
import com.practice.springjpaziyat.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public Product create(Product product, int categoryId) {
        Category category = categoryRepository.findById(categoryId)
                        .orElseThrow(()-> new RuntimeException("Категория с id = " + categoryId + " не существует"));
        product.setCategory(category);
        productRepository.save(product);
        return product;
    }
}
