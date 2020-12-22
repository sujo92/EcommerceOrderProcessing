package com.suziesta.BulkOrder.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.suziesta.BulkOrder.model.Order;
import com.suziesta.BulkOrder.service.BulkOrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/batch")
public class BulkOrderController {
    @Autowired
    private BulkOrderService orderService;


    @PostMapping("/order")
    @ApiOperation(value = "add order")
    public boolean createOrders(@RequestBody Order[] order) throws JsonProcessingException {
        return orderService.saveOrder(order);
    }

    @PutMapping("/status/{statusId}")
    public ResponseEntity<String> updateOrders(@PathVariable String statusId, @RequestBody Order[] orders) throws JsonProcessingException {
        return orderService.updateOrders(statusId, orders);
    }
}
