package com.example.order.order.service;

import com.example.order.order.models.Item;
import com.example.order.order.dao.MenuDao;
import com.example.order.order.exceptions.InvalidOrderError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MenuProcessor {

    @Autowired
    private MenuDao menuDao;


    public Item addItem(Item item) {
        return menuDao.save(item);
    }

    public List<Item> getItems() {
        return menuDao.findAll();
    }

    public Item getItemByName(String name) {
        Optional<Item> requiredItem = menuDao.findById(name);

        if(!requiredItem.isPresent())
            throw new InvalidOrderError("Item not present in the menu");

        return requiredItem.get();
    }
}
