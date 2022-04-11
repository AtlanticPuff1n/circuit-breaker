package com.inventory.demo.controller.api;

import com.inventory.demo.entity.Item;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/item")
public interface InventoryController {

    @ApiOperation(value = "Returns an item by name")
    @GetMapping("/{name}")
    Optional<Item> getItem(@PathVariable String name);

    @ApiOperation(value = "Adds an item to the inventory")
    @PostMapping("/add")
    Item addItem(@RequestBody Item item);
}
