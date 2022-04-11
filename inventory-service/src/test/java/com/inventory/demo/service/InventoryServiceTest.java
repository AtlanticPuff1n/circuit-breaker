package com.inventory.demo.service;

import com.inventory.demo.entity.Item;
import com.inventory.demo.repository.InventoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {InventoryService.class})
@ExtendWith(SpringExtension.class)
class InventoryServiceTest {

    @MockBean
    private InventoryRepository inventoryRepository;

    @Autowired
    private InventoryService inventoryService;

    @Test
    void getItemByName_ItemFound() {
        Item item = Item.builder()
                .id(1L)
                .name("Name")
                .price(100.0f)
                .build();

        when(this.inventoryRepository.findItemByName(any())).thenReturn(Optional.of(item));
        Optional<Item> actualItemByName = this.inventoryService.getItemByName("Name");

        assertTrue(actualItemByName.isPresent());
        assertSame(item, actualItemByName.get());
        verify(this.inventoryRepository).findItemByName(any());
    }

    @Test
    void getItemByName_ItemNotFound() {
        when(this.inventoryRepository.findItemByName(any())).thenReturn(Optional.empty());
        Optional<Item> actualItemByName = this.inventoryService.getItemByName("Name");

        assertTrue(actualItemByName.isEmpty());
        verify(this.inventoryRepository).findItemByName(any());
    }

    @Test
    void addItem_ItemAdded() {
        Item item = Item.builder()
                .id(1L)
                .name("Name")
                .price(100.0f)
                .build();

        when(this.inventoryRepository.save(item)).thenReturn(item);
        Item actualItem = this.inventoryService.addItem(item);

        assertSame(item, actualItem);
        verify(this.inventoryRepository).save(item);
    }

}

