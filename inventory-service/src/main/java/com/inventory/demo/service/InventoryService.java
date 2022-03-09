package com.inventory.demo.service;

import com.inventory.demo.entity.Item;
import com.inventory.demo.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public Optional<Item> getItemByName(String name) {
        return inventoryRepository.findItemByName(name);
    }
}
