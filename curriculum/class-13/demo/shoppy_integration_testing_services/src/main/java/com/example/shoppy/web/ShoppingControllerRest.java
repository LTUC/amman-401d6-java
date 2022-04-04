package com.example.shoppy.web;

import com.example.shoppy.domain.Item;
import com.example.shoppy.infrastructure.ShoppingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/shopping")
public class ShoppingControllerRest {

    private final ShoppingService shoppingService;

    public ShoppingControllerRest(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @GetMapping("/items")
    public Iterable<Item> getItems() {
        // create a item
        Item item = new Item();
        item.setName("Oil");
        item.setCost(BigDecimal.valueOf(23.56));

        // Save inserts or updates a item
        // Because there is no "id" set, it will insert
        shoppingService.saveAnItem(item);

        // returns all the bugs (including the new one)
        Iterable<Item> items = shoppingService.findAllItems();

        // because this is a RestController, everything will be turned
        // into json
        return items;
    }
}
