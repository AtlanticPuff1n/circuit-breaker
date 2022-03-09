package com.order.demo.service;

import com.order.demo.entity.Item;
import com.order.demo.entity.Order;
import com.order.demo.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.order.demo.constants.Constants.ORDER_SERVICE_NAME;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;

    @CircuitBreaker(name = ORDER_SERVICE_NAME, fallbackMethod = "placeOrderFallback")
    public String placeOrder(Order order) {
        Item item = restTemplate.getForObject("http://localhost:8081/item/" + order.getProductName(), Item.class);
        if (item != null) {
            order.setTotalPrice(item.getPrice());
            orderRepository.save(order);
        } else {
            return "Can't find an item with this name";
        }
        return "The order has been successfully placed!";
    }

    private String placeOrderFallback(Exception e) {
        return "The inventory service is down, please try again later.";
    }

}
