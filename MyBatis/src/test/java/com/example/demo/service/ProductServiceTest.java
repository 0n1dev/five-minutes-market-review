package com.example.demo.service;

import com.example.demo.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Test
    void getProducts() {
        Product product = productService.getProductById(1L);

        System.out.println(product.getProdName());
        System.out.println(product.getProdPrice());
        assertThat(product).isNotNull();
    }
}