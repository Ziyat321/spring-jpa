package com.practice.springjpaziyat.repository;

import com.practice.springjpaziyat.model.Category;
import com.practice.springjpaziyat.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option, Long> {
    @Query("select o from Option o join fetch o.category")
    List<Option> findAllWithCategory();
}
