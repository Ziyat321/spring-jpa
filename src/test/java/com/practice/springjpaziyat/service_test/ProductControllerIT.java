package com.practice.springjpaziyat.service_test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.springjpaziyat.model.Product;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ProductControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @SneakyThrows
    void findAllTest() {
        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Intel Core I9 9900"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value(249990))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].categoryName").value("Процессоры"));

    }

    @Test
    @SneakyThrows
    void findByIdTest() {
        mockMvc.perform(MockMvcRequestBuilders.get("/products/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isMap())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Intel Core I9 9900"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(249990))
                .andExpect(MockMvcResultMatchers.jsonPath("$.categoryName").value("Процессоры"));
    }

    @Test
    @SneakyThrows
    void createTest() {
        Product product = new Product();
        product.setName("Product1");
        product.setPrice(1000.0);

        String productJson = objectMapper.writeValueAsString(product);

        mockMvc.perform(MockMvcRequestBuilders.post("/products/1").contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Product1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("$.categoryName").value("Процессоры"));
    }
}
