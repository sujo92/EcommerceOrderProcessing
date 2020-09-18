package com.suziesta.Order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.suziesta.Order.model.Order;

public interface OrderService {
    boolean saveOrder(Order order) throws JsonProcessingException;

    Order getOrderDetails(String id);

    boolean deleteOrder(String id);

    boolean updateOrder(Order order);
}
