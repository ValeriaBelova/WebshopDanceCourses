package com.example.demo.service;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.model.Order;
import com.example.demo.model.OrderDetail;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;  // Om du vill lägga till produkter i beställningen

    public Order createOrder(OrderDTO orderDTO) {
        // Hämta användaren baserat på userId från databasen
        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Användare inte hittad"));

        // Skapa en order och sätt orderdetaljer
        Order order = new Order();
        order.setUser(user);  // Sätt användaren
        order.setOrderDate(LocalDate.now().toString());
        order.setStatus("NY");  // Statusen kan vara t.ex. "NY" för nya beställningar

        // Om du vill lägga till produkter i beställningen (om det finns en relaterad produkt)
        if (orderDTO.getProductIds() != null && !orderDTO.getProductIds().isEmpty()) {
            for (Long productId : orderDTO.getProductIds()) {
                Product product = productRepository.findById(productId)
                        .orElseThrow(() -> new RuntimeException("Produkt inte hittad"));
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setProduct(product);
                orderDetail.setOrder(order);
                orderDetail.setQuantity(1);  // Sätt exempelvis till 1 för att skapa en enstaka beställning
                order.getOrderDetails().add(orderDetail);
            }
        }

        // Spara och returnera den skapade ordern
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, String status) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order inte hittad"));
        order.setStatus(status);
        return orderRepository.save(order);
    }
}