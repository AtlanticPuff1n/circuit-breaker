package com.inventory.demo.controller.api;

import com.inventory.demo.entity.Item;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/item")
public interface InventoryController {

    @ApiOperation(value = "Returns an item by name")
    @GetMapping("/{name}")
    Optional<Item> getItem(@PathVariable String name);

}
