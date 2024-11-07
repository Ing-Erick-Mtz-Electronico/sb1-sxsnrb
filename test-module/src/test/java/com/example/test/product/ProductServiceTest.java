package com.example.test.product;

import com.example.common.entity.Product;
import com.example.product.ProductServiceApplication;
import com.example.product.repository.ProductRepository;
import com.example.test.config.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ProductServiceApplication.class)
@Import(TestConfig.class)
@ActiveProfiles("test")
class ProductServiceTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void createAndRetrieveProduct() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(99.99);
        product.setStock(10);

        Product savedProduct = productRepository.save(product);
        assertNotNull(savedProduct.getId());

        Product retrievedProduct = productRepository.findById(savedProduct.getId()).orElse(null);
        assertNotNull(retrievedProduct);
        assertEquals("Test Product", retrievedProduct.getName());
        assertEquals(99.99, retrievedProduct.getPrice());
        assertEquals(10, retrievedProduct.getStock());
    }
}