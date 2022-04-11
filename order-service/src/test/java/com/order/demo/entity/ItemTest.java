package com.order.demo.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemTest {

    @Test
    void testNoArgsConstructor() {
        Item actualItem = new Item();
        actualItem.setId(1L);
        actualItem.setName("Name");
        actualItem.setPrice(10.0f);

        assertEquals(1L, actualItem.getId().longValue());
        assertEquals("Name", actualItem.getName());
        assertEquals(10.0f, actualItem.getPrice().floatValue());
    }

    @Test
    void testAllArgsConstructorWithBuilder() {
        Item actualItem = Item.builder()
                .id(1L)
                .name("Name")
                .price(10.0f)
                .build();

        assertEquals(1L, actualItem.getId().longValue());
        assertEquals("Name", actualItem.getName());
        assertEquals(10.0f, actualItem.getPrice().floatValue());
    }
}

