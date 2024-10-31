package com.practice.springjpaziyat.repository;

import com.practice.springjpaziyat.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByPriceBetween(double startPrice, double endPrice);
}
