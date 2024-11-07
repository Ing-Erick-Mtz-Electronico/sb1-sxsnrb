package com.example.order.client;

import com.example.common.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", url = "http://localhost:8082")
public interface ProductClient {
    @GetMapping("/api/products/{id}")
    Product getProductById(@PathVariable Long id);
}