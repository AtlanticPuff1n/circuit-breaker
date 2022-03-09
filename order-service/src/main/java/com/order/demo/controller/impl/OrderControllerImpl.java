package com.order.demo.controller.impl;

import com.order.demo.controller.api.OrderController;
import com.order.demo.entity.Order;
import com.order.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerImpl implements OrderController {

    private final OrderService orderService;

    @Override
    public String placeOrder(@RequestBody Order order) {
        return orderService.placeOrder(order);
    }

}
