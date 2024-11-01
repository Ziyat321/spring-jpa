package com.practice.springjpaziyat.controller;

import com.practice.springjpaziyat.dto.ProductDto;
import com.practice.springjpaziyat.model.Category;
import com.practice.springjpaziyat.model.Option;
import com.practice.springjpaziyat.model.Product;
import com.practice.springjpaziyat.model.Value;
import com.practice.springjpaziyat.repository.CategoryRepository;
import com.practice.springjpaziyat.repository.OptionRepository;
import com.practice.springjpaziyat.repository.ProductRepository;
import com.practice.springjpaziyat.repository.ValueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final OptionRepository optionRepository;
    private final ValueRepository valueRepository;

    @GetMapping
    List<ProductDto> findAll() {
        List<ProductDto> productDtoList = new ArrayList<>();
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            List<Option> options = optionRepository.findAllByCategoryOrderByName(product.getCategory());
            List<Value> values = valueRepository.findAllByProductAndOptionInOrderByOption(product, options);
            Map<String, String> valuesMap = new HashMap<>();
            for (int i = 0; i < options.size(); i++) {
                valuesMap.put(options.get(i).getName(), values.get(i).getName());
            }
            ProductDto productDto = ProductDto.of(product, valuesMap);
            productDtoList.add(productDto);
        }
        return productDtoList;
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
                                        @RequestParam Double endPrice) {
        return productRepository.findAllByPriceBetween(startPrice, endPrice);
    }
}
