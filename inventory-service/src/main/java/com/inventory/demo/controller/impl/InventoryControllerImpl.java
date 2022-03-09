package com.inventory.demo.controller.impl;

import com.inventory.demo.controller.api.InventoryController;
import com.inventory.demo.entity.Item;
import com.inventory.demo.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class InventoryControllerImpl implements InventoryController {

    private final InventoryService inventoryService;

    @Override
    public Optional<Item> getItem(@PathVariable String name) {
        return inventoryService.getItemByName(name);
    }

}
