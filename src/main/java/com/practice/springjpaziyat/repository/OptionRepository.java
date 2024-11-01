package com.practice.springjpaziyat.repository;

import com.practice.springjpaziyat.model.Category;
import com.practice.springjpaziyat.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option, Long> {
    List<Option> findAllByCategoryOrderByName(Category category);
}
