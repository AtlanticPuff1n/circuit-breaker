package com.order.demo.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemTest {

    @Test
    void testConstructor() {
        Item actualItem = new Item();
        actualItem.setId(123L);
        actualItem.setName("Name");
        actualItem.setPrice(10.0f);
        assertEquals(123L, actualItem.getId().longValue());
        assertEquals("Name", actualItem.getName());
        assertEquals(10.0f, actualItem.getPrice().floatValue());
    }

    @Test
    void testConstructor2() {
        Item actualItem = new Item(123L, "Name", 10.0f);
        actualItem.setId(123L);
        actualItem.setName("Name");
        actualItem.setPrice(10.0f);
        assertEquals(123L, actualItem.getId().longValue());
        assertEquals("Name", actualItem.getName());
        assertEquals(10.0f, actualItem.getPrice().floatValue());
    }
}

