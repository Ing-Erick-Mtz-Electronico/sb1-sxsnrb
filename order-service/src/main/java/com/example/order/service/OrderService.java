package com.example.order.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.common.entity.Order;
import com.example.common.entity.OrderItem;
import com.example.common.entity.Product;
import com.example.common.entity.User;
import com.example.order.dto.OrderItemDto;
import com.example.order.dto.OrderRequest;
import com.example.order.repository.OrderRepository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final EntityManager entityManager;

    public List<Order> findAllWithDetails() {
        return orderRepository.findAllWithUserAndProducts();
    }

    @Transactional
    public Order createOrder(OrderRequest orderRequest) {
        User user = entityManager.find(User.class, orderRequest.getUserId());
        
        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");
        
        List<OrderItem> items = new ArrayList<>();
        for (OrderItemDto itemRequest : orderRequest.getItems()) {
            Product product = entityManager.find(Product.class, itemRequest.getProductId());
            
            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setQuantity(itemRequest.getQuantity());
            item.setPrice(product.getPrice());
            items.add(item);
        }
        
        order.setItems(items);
        return orderRepository.save(order);
    }
}