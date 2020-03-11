package com.example.order.order.apis;

import com.example.order.order.models.Item;
import com.example.order.order.service.MenuProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/menu")
public class MenuResource {

    @Autowired
    private MenuProcessor menuProcessor;

    @PostMapping
    public Item addInventory(@RequestBody Item item) {
        System.out.println(item);
        return menuProcessor.addItem(item);
    }

    @GetMapping
    public List<Item> getInventory() {
        return menuProcessor.getItems();
    }
}
