package com.practice.springjpaziyat.service_test;

import com.practice.springjpaziyat.model.Category;
import com.practice.springjpaziyat.model.Product;
import com.practice.springjpaziyat.repository.CategoryRepository;
import com.practice.springjpaziyat.repository.ProductRepository;
import com.practice.springjpaziyat.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    CategoryRepository categoryRepository;
    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    @Test
    public void shouldCreateProductWhenCategoryExists(){
//        CategoryRepository categoryRepository = Mockito.mock(CategoryRepository.class);
//        ProductRepository productRepository = Mockito.mock(ProductRepository.class);

        Mockito.when(categoryRepository.findById(Mockito.anyInt()))
                .thenAnswer(invocationOnMock -> {
                    int categoryId = invocationOnMock.getArgument(0);
                    Category category = new Category();
                    category.setId(categoryId);
                    category.setName("Category1");
                    return Optional.of(category);
                });
        Mockito.when(productRepository.save(Mockito.any(Product.class)))
                .thenAnswer(invocationOnMock -> invocationOnMock.<Product>getArgument(0));

//        ProductService productService = new ProductService(productRepository, categoryRepository);

        Product product = new Product();

        product.setName("Product1");
        product.setPrice(1000D);

        Product productResult = productService.create(product, 1);
        assertEquals(productResult.getName(), "Product1");
        assertEquals(productResult.getPrice(), 1000);
        assertEquals(productResult.getCategory().getId(), 1);
        assertEquals(productResult.getCategory().getName(), "Category1");
    }

    @Test
    public void shouldThrowExceptionWhenCategoryDoesNotExist(){
        int categoryId =1;
//        CategoryRepository categoryRepository = Mockito.mock(CategoryRepository.class);
//        ProductRepository productRepository = Mockito.mock(ProductRepository.class);

//        Mockito.when(categoryRepository.findById(Mockito.anyInt()))
//                .thenThrow(new RuntimeException("Категория с id = " + 1 + " не существует"));

//        ProductService productService = new ProductService(productRepository, categoryRepository);

        Product product = new Product();
        product.setName("Product1");
        product.setPrice(1000D);

        RuntimeException ex =
                assertThrows(RuntimeException.class,()->productService.create(product, categoryId));

        assertEquals("Категория с id = " + categoryId + " не существует", ex.getMessage());
    }
}
