package com.suziesta.Order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.suziesta.Order.model.Order;

import java.util.List;

public interface OrderService {
    boolean saveOrder(Order order) throws JsonProcessingException;

    List<Order> getOrderDetails(String id);

    boolean deleteOrder(String id);

    boolean updateOrder(Order order);
}
