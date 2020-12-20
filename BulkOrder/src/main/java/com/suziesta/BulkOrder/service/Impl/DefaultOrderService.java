package com.suziesta.BulkOrder.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.suziesta.BulkOrder.model.Order;
import com.suziesta.BulkOrder.service.BulkOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class DefaultOrderService implements BulkOrderService {
    private static final Logger LOGGER= LoggerFactory.getLogger(BulkOrderService.class);

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    @Value("${kafka.topic.name.create}")
    private String createTopicName;

    @Value("${kafka.topic.name.update}")
    private String updateTopicName;

    @Override
    public boolean saveOrder(Order[] orders) throws JsonProcessingException {
        for(Order ord: orders) {
            LOGGER.info("Before sending saveOrder data to kafka");
            kafkaTemplate.send(createTopicName,ord);
            LOGGER.info("Save Order - "+ord.toString()+"send to kafka topic-"+createTopicName);
        }
        return true;
    }

    @Override
    public boolean updateOrders(String statusId, Order[] orders) throws JsonProcessingException {
        for(Order ord: orders) {
            ord.setStatus(statusId);
            System.out.println(ord);
            LOGGER.info("Before sending updateOrder data to kafka");
            kafkaTemplate.send(updateTopicName,ord);
            LOGGER.info("Update Order - "+ord.toString()+"send to kafka topic-"+updateTopicName );
        }
        return true;
    }

}
