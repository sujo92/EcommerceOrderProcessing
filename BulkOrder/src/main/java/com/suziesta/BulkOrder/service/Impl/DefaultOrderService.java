package com.suziesta.BulkOrder.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.net.httpserver.Authenticator;
import com.suziesta.BulkOrder.model.Order;
import com.suziesta.BulkOrder.repository.OrderRepository;
import com.suziesta.BulkOrder.service.BulkOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class DefaultOrderService implements BulkOrderService {
    private static final Logger LOGGER= LoggerFactory.getLogger(BulkOrderService.class);

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    @Autowired
    private OrderRepository orderRepository;

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
    public ResponseEntity<String> updateOrders(String statusId, Order[] orders) throws JsonProcessingException {
        boolean completeUpdate = true;
        for(Order ord: orders) {
            if(orderRepository.findById(ord.getOrderId()).isPresent()) {
                ord.setStatus(statusId);
                System.out.println(ord);
                LOGGER.info("Before sending updateOrder data to kafka");
                kafkaTemplate.send(updateTopicName, ord);
                LOGGER.info("Update Order - " + ord.toString() + "send to kafka topic-" + updateTopicName);
            }else{
                LOGGER.error("invalid order id:"+ord.getOrderId());
                completeUpdate=false;
            }
        }

        return completeUpdate==true?
                        new ResponseEntity<>(HttpStatus.OK):
                        new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
