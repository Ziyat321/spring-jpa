package com.practice.springjpaziyat.repository;

import com.practice.springjpaziyat.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByPriceBetween(double startPrice, double endPrice);

    @Query("select p from Product p join fetch p.category c join fetch c.options")
    List<Product> findAllWithCategoryAndOptions();
}
