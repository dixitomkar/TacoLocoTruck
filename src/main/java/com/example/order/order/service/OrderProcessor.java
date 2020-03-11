package com.example.order.order.service;

import com.example.order.order.models.Item;
import com.example.order.order.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderProcessor {

    @Autowired
    private MenuProcessor menuProcessor;

    public double getTotal(List<Order> orders) {
        double totalPrice = 0;
        for(Order order: orders) {
            Item curr = menuProcessor.getItemByName(order.getName());
            double currPrice = order.getQuantity() * curr.getPrice();
            totalPrice += currPrice;
        }

        return totalPrice;
    }
}
