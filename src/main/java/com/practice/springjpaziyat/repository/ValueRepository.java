package com.practice.springjpaziyat.repository;

import com.practice.springjpaziyat.model.Option;
import com.practice.springjpaziyat.model.Product;
import com.practice.springjpaziyat.model.Value;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ValueRepository extends JpaRepository<Value, Long> {
    List<Value> findAllByProductAndOptionInOrderByOption(Product product, Collection<Option> options);
}
