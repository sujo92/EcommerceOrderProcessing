package com.suziesta.BulkOrder.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.suziesta.BulkOrder.model.Order;
import org.springframework.http.ResponseEntity;

public interface BulkOrderService {
    boolean saveOrder(Order[] order) throws JsonProcessingException;
    ResponseEntity<String> updateOrders(String statusId, Order[] orders) throws JsonProcessingException;

}
