package com.suziesta.Order.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.suziesta.Order.model.Order;
import com.suziesta.Order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/order")
    public boolean addVehicle(@RequestBody Order order) throws JsonProcessingException {
        orderService.saveOrder(order);
        return true;
    }

    @GetMapping("/order/{id}")
    public List<Order> getOrder(@PathVariable String id){
        return orderService.getOrderDetails(id);
    }

    @DeleteMapping("/order/{id}")
    public boolean deleteOrder(@PathVariable String id){
        return orderService.deleteOrder(id);
    }
}
