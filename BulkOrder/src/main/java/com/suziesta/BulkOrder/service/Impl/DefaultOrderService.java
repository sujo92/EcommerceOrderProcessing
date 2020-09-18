package com.suziesta.BulkOrder.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.suziesta.BulkOrder.AwsMessaging.BulkOrderSns;
import com.suziesta.BulkOrder.AwsMessaging.BulkOrderUpdateSns;
import com.suziesta.BulkOrder.model.Order;
import com.suziesta.BulkOrder.service.BulkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DefaultOrderService implements BulkOrderService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    BulkOrderSns bulkOrderSns;

    @Autowired
    BulkOrderUpdateSns bulkOrderUpdateSns;

    @Autowired
    ObjectMapper objectMapper;

    public DefaultOrderService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean saveOrder(Order[] orders) throws JsonProcessingException {
        for(Order ord: orders) {
//            restTemplate.postForObject("http://localhost:8081/app/order", ord, boolean.class);
            String StringOrder = objectMapper.writeValueAsString(ord);
            bulkOrderSns.send("order sent",StringOrder);
        }
        return true;
    }

    @Override
    public boolean updateOrders(String statusId, Order[] orders) throws JsonProcessingException {
        for(Order ord: orders) {
            ord.setStatus(statusId);
            System.out.println(ord);
            String resourceUrl = "http://localhost:8081/app/order";
            restTemplate.put(resourceUrl, ord);
//            String StringOrder = objectMapper.writeValueAsString(ord);
//            System.out.println("string Ord: "+ord);
//            bulkOrderUpdateSns.send("order update sent",StringOrder);
        }
        return true;
    }

}
