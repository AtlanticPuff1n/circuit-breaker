package com.inventory.demo.repository;

import com.inventory.demo.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Item, Long> {
    Optional<Item> findItemByName(String name);
}
