package com.suziesta.Order.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import java.util.List;

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
    public List<Order> getOrderDetails(String id) {
        List<Order> orderList = orderRepository.findAllbyId(id);
        return orderList;
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
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        System.out.println(order.getOrderId()+" : "+order.getStatus());
        orderRepository.updateStatus(order.getOrderId(),timestamp, order.getStatus());
        return true;
    }
}
