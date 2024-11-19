package com.practice.springjpaziyat.service_test;

import com.practice.springjpaziyat.model.Category;
import com.practice.springjpaziyat.model.Product;
import com.practice.springjpaziyat.repository.CategoryRepository;
import com.practice.springjpaziyat.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase
public class ProductRepositoryIT {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;

    @Test
    public void saveProductWhenCategoryIsGiven() {
        Category category = new Category();
        category.setName("Category1");
        categoryRepository.save(category);
        Product product = new Product();
        product.setName("Product1");
        product.setPrice(1000D);
        product.setCategory(category);
        productRepository.save(product);

        Optional<Product> productOptional = productRepository.findById(1);
        assertTrue(productOptional.isPresent());
        assertEquals(1, productOptional.get().getId());
        assertEquals("Product1", productOptional.get().getName());
        assertEquals(1000, productOptional.get().getPrice());
        assertEquals("Category1", productOptional.get().getCategory().getName());
    }

    @Test
    public void shouldThrowExceptionWhenCategoryIsNotGiven() {
        Product product = new Product();
        product.setName("Product1");
        product.setPrice(1000D);

        assertThrows(DataIntegrityViolationException.class, ()->productRepository.save(product));
    }

    @Test
    public void shouldReturnFirstPageSortedByPrice() {
        Category category = new Category();
        category.setName("Category1");
        categoryRepository.save(category);

        for (int i = 1; i <= 10; i++) {
            Product product = new Product();
            product.setName("Product" + i);
            product.setPrice(1000.0 + i);
            product.setCategory(category);
            productRepository.save(product);
        }

        Sort sort = Sort.by("price").descending();
        PageRequest pageRequest = PageRequest.of(0, 5, sort);
        Page<Product> products = productRepository.findAll(pageRequest);

        int idExpected = 10;
        for (Product product : products) {
            assertEquals(idExpected, product.getId());
            assertEquals("Product" + idExpected, product.getName());
            assertEquals(1000 + idExpected--, product.getPrice());
            assertEquals("Category1", product.getCategory().getName());
        }
    }
}
