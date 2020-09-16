package com.suziesta.Order.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.suziesta.Order.model.Order;
import com.suziesta.Order.repository.OrderRepository;
import com.suziesta.Order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultOrderService implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public boolean saveOrder(Order order) throws JsonProcessingException {
        orderRepository.save(order);
        return true;
    }

    @Override
    public List<Order> getOrderDetails(String id) {
        return orderRepository.findAllbyId(id);
    }
}
