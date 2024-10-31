package com.practice.springjpaziyat.repository;

import com.practice.springjpaziyat.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findByNameIgnoreCase(String name);

    List<Category> findAllByNameContainingIgnoreCase(String name);
}
