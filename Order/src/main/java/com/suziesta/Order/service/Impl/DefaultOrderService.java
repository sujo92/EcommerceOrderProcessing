package com.suziesta.Order.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.suziesta.Order.exceptions.ResourceNotFoundException;
import com.suziesta.Order.model.Order;
import com.suziesta.Order.repository.OrderRepository;
import com.suziesta.Order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class DefaultOrderService implements OrderService {
    private Logger log = LoggerFactory.getLogger(DefaultOrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public boolean saveOrder(Order order) throws JsonProcessingException {
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        order.setCreatedDate(timestamp);
        order.setModifiedDate(timestamp);
        orderRepository.save(order);
        return true;
    }

    @Override
    public Order getOrderDetails(String id) {
        return  orderRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Order with id"+id+"doesnt exist"));
    }

    @Override
    public boolean deleteOrder(String id) {
        try {
            orderRepository.deleteById(id);
        }
        catch (Exception e){
            new ResourceNotFoundException("Order with id"+id+"doesnt exist");
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean updateOrder(Order order) {
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        orderRepository.updateStatus(order.getOrderId(),timestamp, order.getStatus());
        return true;
    }
}
