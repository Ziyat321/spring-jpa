package com.practice.springjpaziyat.controller;

import com.practice.springjpaziyat.model.Category;
import com.practice.springjpaziyat.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryRepository categoryRepository;

    @GetMapping
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @GetMapping("/{id}")
    public Category findById(@PathVariable int id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    @GetMapping("/{name}")
    public Category findByName(@PathVariable String name) {
        return categoryRepository.findByNameIgnoreCase(name).orElseThrow();
    }


    @PostMapping
    public Category create(@RequestBody Category category) {
        return categoryRepository.save(category);
    }
}
