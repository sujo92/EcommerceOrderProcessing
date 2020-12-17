package com.suziesta.BulkOrder.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.suziesta.BulkOrder.model.Order;
import com.suziesta.BulkOrder.service.BulkOrderService;
import org.apache.kafka.common.protocol.types.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class DefaultOrderService implements BulkOrderService {

//    @Autowired
//    RestTemplate restTemplate;
    private static final Logger LOGGER= LoggerFactory.getLogger(BulkOrderService.class);

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    @Value("${kafka.topic.name.create}")
    private String createTopicName;

    @Value("${kafka.topic.name.update}")
    private String updateTopicName;

//    public DefaultOrderService(RestTemplate restTemplate){
//        this.restTemplate = restTemplate;
//    }

    @Override
    public boolean saveOrder(Order[] orders) throws JsonProcessingException {
        for(Order ord: orders) {
//            restTemplate.postForObject("http://localhost:8081/app/order", ord, boolean.class);
            kafkaTemplate.send(createTopicName,ord);
            LOGGER.info("Save Order - "+ord.toString()+"send to kafka topic-"+createTopicName);
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
 //           String resourceUrl = "http://localhost:8081/app/order";
//            restTemplate.put(resourceUrl, ord);
            kafkaTemplate.send(updateTopicName,ord);
            LOGGER.info("Update Order - "+ord.toString()+"send to kafka topic-"+updateTopicName );
        }
        return true;
    }

}
