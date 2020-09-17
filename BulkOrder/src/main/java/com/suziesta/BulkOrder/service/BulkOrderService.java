package com.suziesta.BulkOrder.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.suziesta.BulkOrder.model.Order;

public interface BulkOrderService {
    boolean saveOrder(Order[] order) throws JsonProcessingException;
    boolean updateOrders(String statusId, Order[] orders);
}
