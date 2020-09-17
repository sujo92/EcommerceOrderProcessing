package com.suziesta.Order.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.suziesta.Order.model.Order;
import com.suziesta.Order.repository.OrderRepository;
import com.suziesta.Order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Override
    public boolean deleteOrder(String id) {
        orderRepository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean updateOrder(Order order) {
        System.out.println("orderservice: status update");
        System.out.println(order.getOrderId()+" : "+order.getStatus());
        orderRepository.updateStatus(order.getStatus() , order.getOrderId());
        return true;
    }
}
