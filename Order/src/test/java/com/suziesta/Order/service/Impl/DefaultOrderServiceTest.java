package com.suziesta.Order.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.suziesta.Order.model.Order;
import com.suziesta.Order.repository.OrderRepository;
import com.suziesta.Order.service.OrderService;
import com.suziesta.Order.util.getOrderData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class DefaultOrderServiceTest {

    @Autowired
    private OrderService orderService;

    @MockBean
    private OrderRepository orderRepository;

    @TestConfiguration
    static class OrderServiceImplTestConfig{
        @Bean
        public OrderService getService() {
            return new DefaultOrderService();
        }
    }

    List<Order> orderList= new ArrayList<>();

    @Before
    public void setup(){
        orderList.add(getOrderData.getOrder());
        Mockito.when(orderRepository.findById(getOrderData.getOrder().getOrderId()))
                .thenReturn(java.util.Optional.ofNullable(orderList.get(0)));
        String id =getOrderData.getOrder().getOrderId();
    }

    @Test
    public void saveOrder() throws JsonProcessingException {
        Order newOrder = getOrderData.getOrder2();
        boolean res = orderService.saveOrder(newOrder);
        Mockito.when(orderRepository.save(newOrder)).thenReturn(newOrder);
        Assert.assertEquals("return true", true, res);
    }

    @Test
    public void getOrderDetails() {
        Order result = orderService.getOrderDetails(orderList.get(0).getOrderId());
        Assert.assertEquals("Order match",orderList.get(0), result);
    }

    @Test
    public void deleteOrder() {
        String id = orderList.get(0).getOrderId();
//        boolean ans = orderService.deleteOrder(id);
        
    }

    @Test
    public void updateOrder() {
        Order updateOrder = orderList.get(0);
        updateOrder.setCustomerId(30);
//        boolean res = orderService.updateOrder(updateOrder);
//        Mockito.when(orderRepository.updateStatus();)
    }
}