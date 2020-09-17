package com.suziesta.BulkOrder.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.suziesta.BulkOrder.model.Order;
import com.suziesta.BulkOrder.service.BulkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        System.out.println("service: status update");
        System.out.println(statusId);
        for(Order ord: orders) {
            ord.setStatus(statusId);
            String resourceUrl = "http://localhost:8081/app/order";
            restTemplate.put(resourceUrl, ord, Void.class);

//            System.out.println(ord);
//            params.put("status",statusId);
//            ord.setStatus(statusId);
//            restTemplate.put("http://localhost:8081/app/order/{status}", ord, params);


        }
        return true;
    }

//    RequestCallback requestCallback(final Order updatedInstance) {
//        return clientHttpRequest -> {
//            ObjectMapper mapper = new ObjectMapper();
//            mapper.writeValue(clientHttpRequest.getBody(), updatedInstance);
//            clientHttpRequest.getHeaders().add(
//                    HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//        };
//    }

}
