package com.example.crudRepository.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.crudRepository.model.Order;
import com.example.crudRepository.model.Product;
import com.example.crudRepository.model.dto.OrderRequest;
import com.example.crudRepository.repository.OrderRepository;
import com.example.crudRepository.repository.ProductRepository;

@Service
public class TransactionalServiceImpl {
  
  private ProductRepository productRepository;
  
  private OrderRepository orderRepository;
  
  public TransactionalServiceImpl(ProductRepository productRepository, OrderRepository orderRepository) {
    this.productRepository = productRepository;
    this.orderRepository = orderRepository;
  }
  
  @Transactional()
  public Order processOrder(OrderRequest orderRequest) {
    Product product = productRepository.findById(orderRequest.getProductId())
        .orElseThrow(() -> new RuntimeException("Product not found"));

    if (product.getStock() < orderRequest.getQuantity()) {
      throw new RuntimeException("Insufficient stock");
    }

    product.setStock(product.getStock() - orderRequest.getQuantity());
    productRepository.save(product);
    
    Order order = new Order();
    order.setProductId(orderRequest.getProductId());
    order.setQuantity(orderRequest.getQuantity());
    Order orderResponse = orderRepository.save(order);
        
    // Simulate payment
    boolean paymentSuccess = mockPaymentGateway(orderRequest.getQuantity());
    if (!paymentSuccess) {
      throw new RuntimeException("Payment failed");
    }
    
    return orderResponse;
  }

  private boolean mockPaymentGateway(int quantity) {
    return quantity <= 5; // Simulate failure for large orders
  }
  
}
