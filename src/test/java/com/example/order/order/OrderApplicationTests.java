package com.example.order.order;


import com.example.order.order.models.Item;
import com.example.order.order.models.Order;
import com.example.order.order.models.OrderTotal;
import com.example.order.order.service.OrderProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderApplicationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private OrderProcessor orderProcessor;

    @LocalServerPort
    int randomServerPort;

    @BeforeEach
    void init()  throws URISyntaxException {
        initializeItems();

    }

    @Test
    public void checkOrderTotalOneItem() throws URISyntaxException
    {
        String orderUrl = "http://localhost:"+randomServerPort+"/order/";
        URI uri = new URI(orderUrl);
        HttpHeaders headers = new HttpHeaders();
        Order order = new Order("veggie" , 2);
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        HttpEntity<List<Order>> request = new HttpEntity<>(orders, headers);
        ResponseEntity<OrderTotal> result = this.testRestTemplate.postForEntity(uri, request, OrderTotal.class);

        assertEquals("Get order total for 1 item should be successful" , "200 OK", result.getStatusCode().toString());
        assertEquals("Total value for 1 item should be correct" , 4.0, result.getBody().getTotal());
    }

    @Test
    public void checkOrderTotalMultipleItems() throws URISyntaxException
    {
        String orderUrl = "http://localhost:"+randomServerPort+"/order/";
        URI uri = new URI(orderUrl);
        HttpHeaders headers = new HttpHeaders();
        Order order1 = new Order("veggie" , 1);
        Order order2 = new Order("cheese" , 2);

        List<Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);

        HttpEntity<List<Order>> request = new HttpEntity<>(orders, headers);
        ResponseEntity<OrderTotal> result = this.testRestTemplate.postForEntity(uri, request, OrderTotal.class);

        assertEquals("Get order total for multiple items should be successful" , "200 OK", result.getStatusCode().toString());
        assertEquals("Total value for multiple items should be correct" , 9.0, result.getBody().getTotal());
    }

    @Test
    public void checkOrderTotalForDecimalValues() throws URISyntaxException
    {
        String orderUrl = "http://localhost:"+randomServerPort+"/order/";
        URI uri = new URI(orderUrl);
        HttpHeaders headers = new HttpHeaders();
        Order order1 = new Order("veggie" , 1);
        Order order2 = new Order("cheese" , 1);

        List<Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);

        HttpEntity<List<Order>> request = new HttpEntity<>(orders, headers);
        ResponseEntity<OrderTotal> result = this.testRestTemplate.postForEntity(uri, request, OrderTotal.class);

        assertEquals("Decimal values in item price should be handled correctly." , "200 OK", result.getStatusCode().toString());
        assertEquals("Decimal values in item price should be handled correctly." , 5.5, result.getBody().getTotal());
    }

    @Test
    public void checkOrderWithPromotion() throws URISyntaxException
    {
        String orderUrl = "http://localhost:"+randomServerPort+"/order/";
        URI uri = new URI(orderUrl);
        HttpHeaders headers = new HttpHeaders();
        Order order1 = new Order("veggie" , 2);
        Order order2 = new Order("cheese" , 2);

        List<Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);

        HttpEntity<List<Order>> request = new HttpEntity<>(orders, headers);
        ResponseEntity<OrderTotal> result = this.testRestTemplate.postForEntity(uri, request, OrderTotal.class);

        assertEquals("Discount should be applied for orders with items more than 4" , "200 OK", result.getStatusCode().toString());
        assertEquals("Discount should be applied for orders with items more than 4" , 8.8, result.getBody().getTotal());
    }

    @Test
    public void checkInvalidOrderResponse() throws URISyntaxException
    {
        String orderUrl = "http://localhost:"+randomServerPort+"/order/";
        URI uri = new URI(orderUrl);
        HttpHeaders headers = new HttpHeaders();
        Order order1 = new Order("veggie" , 1);
        Order order2 = new Order("cheese11" , 1);   // Invalid item

        List<Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);

        HttpEntity<List<Order>> request = new HttpEntity<>(orders, headers);
        ResponseEntity<OrderTotal> result = this.testRestTemplate.postForEntity(uri, request, OrderTotal.class);

        assertEquals("Should return 400 for items not present in menu" , "400 BAD_REQUEST", result.getStatusCode().toString());
    }



    void initializeItems() throws URISyntaxException {
        String menuUrl = "http://localhost:"+randomServerPort+"/menu/";
        URI uri = new URI(menuUrl);
        HttpHeaders headers = new HttpHeaders();
        Item item1 = new Item("veggie" , 2);
        Item item2 = new Item("cheese" , 3.5);
        Item item3 = new Item("basil" , 2.5);

        List<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        items.add(item3);

        for(Item item: items) {
            HttpEntity<Item> request = new HttpEntity<>(item, headers);
            ResponseEntity<Item> result = this.testRestTemplate.postForEntity(uri, request, Item.class);
        }

    }
}
