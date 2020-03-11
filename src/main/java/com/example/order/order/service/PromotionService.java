package com.example.order.order.service;

import com.example.order.order.models.Order;

import java.util.List;

public class PromotionService {

    final double discount = 20.0;
    final int minimumQuantity = 4;

    public boolean isPromotionAvailable(List<Order> orders) {
        int totalQuantity = 0;
        for(Order order: orders) {
            totalQuantity+= order.getQuantity();
            if(totalQuantity >= minimumQuantity)
                return true;
        }
        return false;
    }

    public double getDiscount(List<Order> orders, double totalAmount) {
        double totalDiscount = 0;
        if(isPromotionAvailable(orders)) {
            totalDiscount = totalAmount * discount / 100;
        }

        return totalDiscount;
    }
}
