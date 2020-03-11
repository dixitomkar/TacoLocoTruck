package com.example.order.order.apis;

import com.example.order.order.models.Order;
import com.example.order.order.service.OrderProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderResource {

    @Autowired
    private OrderProcessor orderProcessor;

    @PostMapping
    public double getTotal(@RequestBody List<Order> orders) {
        return orderProcessor.getTotal(orders);
    }
}
