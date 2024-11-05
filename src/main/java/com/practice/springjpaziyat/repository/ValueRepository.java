package com.practice.springjpaziyat.repository;

import com.practice.springjpaziyat.model.Option;
import com.practice.springjpaziyat.model.Product;
import com.practice.springjpaziyat.model.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ValueRepository extends JpaRepository<Value, Long> {
    @Query("select v from Value v join fetch v.product p join fetch v.option o order by o.name")
    List<Value> findAllByProductAndOptionInOrderByOption(Product product, Collection<Option> options);


    Optional<Value> findByProductAndOption(Product product, Option option);
}
