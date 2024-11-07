package com.example.test.order;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import com.example.common.entity.Order;
import com.example.common.entity.Product;
import com.example.common.entity.User;
import com.example.order.OrderServiceApplication;
import com.example.order.dto.OrderItemDto;
import com.example.order.dto.OrderRequest;
import com.example.order.repository.OrderRepository;
import com.example.order.service.OrderService;
import com.example.product.repository.ProductRepository;
import com.example.test.config.TestConfig;
import com.example.user.repository.UserRepository;

// @SpringBootTest(classes = TestConfig.class)
@SpringBootTest(classes = OrderServiceApplication.class)
@Import(TestConfig.class)
@ActiveProfiles("test")
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    private User testUser;
    private Product testProduct;

    @BeforeEach
    void setUp() {
        // Create test user
        User user = new User();
        user.setName("Test User");
        user.setEmail("test@example.com");
        testUser = userRepository.save(user);

        // Create test product
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(99.99);
        product.setStock(10);
        testProduct = productRepository.save(product);
    }

    @Test
    void createAndRetrieveOrder() {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setUserId(testUser.getId());
        
        OrderItemDto itemRequest = new OrderItemDto();
        itemRequest.setProductId(testProduct.getId());
        itemRequest.setQuantity(2);
        
        orderRequest.setItems(Collections.singletonList(itemRequest));

        Order savedOrder = orderService.createOrder(orderRequest);
        assertNotNull(savedOrder.getId());

        Order retrievedOrder = orderRepository.findById(savedOrder.getId()).orElse(null);
        assertNotNull(retrievedOrder);
        assertEquals(testUser.getId(), retrievedOrder.getUser().getId());
        assertEquals(1, retrievedOrder.getItems().size());
        assertEquals(testProduct.getId(), retrievedOrder.getItems().get(0).getProduct().getId());
    }
}