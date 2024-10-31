package com.practice.springjpaziyat.controller;

import com.practice.springjpaziyat.model.Category;
import com.practice.springjpaziyat.model.Product;
import com.practice.springjpaziyat.repository.CategoryRepository;
import com.practice.springjpaziyat.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @GetMapping
    List<Product> findAll() {
        return productRepository.findAll();
    }

    @PostMapping("/{categoryId}")
    public Product create(@PathVariable int categoryId,
                          @RequestBody Product product) {
        Category category = categoryRepository.findById(categoryId).orElseThrow();
        product.setCategory(category);
        return productRepository.save(product);
    }

    @GetMapping("/price")
    List<Product> findAllByPriceBetween(@RequestParam Double startPrice,
                                        @RequestParam Double endPrice){
        return productRepository.findAllByPriceBetween(startPrice, endPrice);
    }
}
