package com.order.demo.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderTest {

    @Test
    void testNoArgsConstructor() {
        Order actualOrder = new Order();
        actualOrder.setId(1L);
        actualOrder.setProductName("Product Name");
        actualOrder.setTotalPrice(10.0f);

        assertEquals(1L, actualOrder.getId().longValue());
        assertEquals("Product Name", actualOrder.getProductName());
        assertEquals(10.0f, actualOrder.getTotalPrice().floatValue());
    }

    @Test
    void testAllArgsConstructorWithBuilder() {
        Order actualOrder = Order.builder()
                .id(1L)
                .productName("Product Name")
                .totalPrice(10.0f)
                .build();

        assertEquals(1L, actualOrder.getId().longValue());
        assertEquals("Product Name", actualOrder.getProductName());
        assertEquals(10.0f, actualOrder.getTotalPrice().floatValue());
    }
}

