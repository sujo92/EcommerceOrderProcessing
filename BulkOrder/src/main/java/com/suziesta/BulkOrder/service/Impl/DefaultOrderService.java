package com.suziesta.BulkOrder.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.suziesta.BulkOrder.model.Order;
import com.suziesta.BulkOrder.service.BulkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DefaultOrderService implements BulkOrderService {

    @Autowired
    RestTemplate restTemplate;

    public DefaultOrderService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean saveOrder(Order[] orders) throws JsonProcessingException {
        for(Order ord: orders) {
            restTemplate.postForObject("http://localhost:8081/app/order", ord, boolean.class);
        }
        return true;
    }

    @Override
    public boolean updateOrders(String statusId, Order[] orders) {
        System.out.println("service: status update");
        System.out.println(statusId);
        for(Order ord: orders) {
            ord.setStatus(statusId);
            System.out.println(ord);
            String resourceUrl = "http://localhost:8081/app/order";
            restTemplate.put(resourceUrl, ord);
        }
        return true;
    }

}
