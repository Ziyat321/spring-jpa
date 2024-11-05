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

import java.util.*;

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
        // select p from Product p join fetch p.category left join fetch p.values
        // select o from Option o
        List<Product> products = productRepository.findAllWithCategoryAndOptions();


        // PRODUCTS
        // name
        // id
        // price
        // category
        // values = [Apple]

        // name
        // id
        // category
        // values = [Intel, LGA1150]

        for (Product product : products) {
            Map<String, String> valuesMap = new HashMap<>();
            for (Option option : product.getCategory().getOptions()) {
                valueRepository.findByProductAndOption(product, option)
                        .ifPresentOrElse(value -> valuesMap.put(option.getName(), value.getName()),
                                () -> valuesMap.put(option.getName(), null));

            }
            productDtoList.add(ProductDto.of(product, valuesMap));
        }

        // option [Производитель, Сокет]

//        // select values
//        List<Value> values = valueRepository.findAllByProductAndOptionInOrderByOption(product, options);
//        for (Product product : products) {
//            Map<String, String> valuesMap = new HashMap<>();
//            int size = Math.min(options.size(), values.size());
//            for (int i = 0; i < size; i++) {
//                valuesMap.put(options.get(i).getName(), values.get(i).getName());
//            }
//            ProductDto productDto = ProductDto.of(product, valuesMap);
//            productDtoList.add(productDto);
//        }
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
