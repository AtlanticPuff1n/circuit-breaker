package com.order.demo.service;

import com.order.demo.entity.Item;
import com.order.demo.entity.Order;
import com.order.demo.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {OrderService.class})
@ExtendWith(SpringExtension.class)
class OrderServiceTest {

    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    void placeOrder_ProductPlaced() throws RestClientException {
        when(this.restTemplate.getForObject(any(), any(), (Object[]) any())).thenReturn(new Item());

        Order order1 = Order.builder()
                .id(1L)
                .productName("Product Name")
                .totalPrice(10.0f)
                .build();

        assertEquals("The order has been successfully placed!", this.orderService.placeOrder(order1));
        verify(this.restTemplate).getForObject(any(), any(), (Object[]) any());
        verify(this.orderRepository).save(any());
        assertNull(order1.getTotalPrice());
    }

    @Test
    void placeOrder_ProductNotPlaced() throws RestClientException {
        when(this.restTemplate.getForObject(any(), any(), (Object[]) any())).thenReturn(null);

        Order order1 = Order.builder()
                .id(1L)
                .productName("Product Name")
                .totalPrice(10.0f)
                .build();

        assertEquals("Can't find an item with this name", this.orderService.placeOrder(order1));
        verify(this.restTemplate).getForObject(any(), any(), (Object[]) any());
    }
}

