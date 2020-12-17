package com.suziesta.Order.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.suziesta.Order.model.Order;
import com.suziesta.Order.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app")
public class OrderController {

    private static final Logger LOGGER= LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @PostMapping("/order")
    @ApiOperation(value = "add order")
    public boolean createOrder(@RequestBody Order order) throws JsonProcessingException {
        orderService.saveOrder(order);
        return true;
    }

    @KafkaListener(topics = "${kafka.topic.name.create}", groupId = "${kafka.consumer.group.id1}", containerFactory = "userKafkaListenerContainerFactory")
    public boolean getUser(Order order) throws JsonProcessingException {
        orderService.saveOrder(order);
        LOGGER.info("Create Order -" + order.toString() + " received");
        return true;
    }

    @KafkaListener(topics = "${kafka.topic.name.update}", groupId = "${kafka.consumer.group.id2}", containerFactory = "updateUserKafkaListenerContainerFactory")
    public boolean updateUser(Order order) throws JsonProcessingException {
        orderService.updateOrder(order);
        LOGGER.info("Update Order -" + order.toString() + " received");
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
