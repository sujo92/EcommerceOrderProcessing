package com.suziesta.Order.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.suziesta.Order.model.Order;
import com.suziesta.Order.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/order")
    @ApiOperation(value = "add order")
    public boolean createOrder(@RequestBody Order order) throws JsonProcessingException {
        orderService.saveOrder(order);
        return true;
    }

    @GetMapping("/order/{id}")
    @ApiOperation(value = "get order by orderid",response= Order.class, responseContainer = "List")
    public List<Order> getOrder(@PathVariable String id){
        return orderService.getOrderDetails(id);
    }

    @DeleteMapping("/order/{id}")
    @ApiOperation(value = "delete order by orderId")
    public boolean deleteOrder(@PathVariable String id){
        return orderService.deleteOrder(id);
    }

    @PutMapping("/order")
    public boolean updateOrder(@RequestBody Order order){
//        System.out.println(order);
        System.out.println("ordercontroller: status update "+order.getOrderId()+":"+order.getStatus());
        return orderService.updateOrder(order);
    }
}
