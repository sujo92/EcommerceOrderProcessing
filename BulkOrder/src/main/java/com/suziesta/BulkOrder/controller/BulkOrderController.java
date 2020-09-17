package com.suziesta.BulkOrder.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.suziesta.BulkOrder.model.Order;
import com.suziesta.BulkOrder.service.BulkOrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/batch")
public class BulkOrderController {
    @Autowired
    private BulkOrderService orderService;


    @PostMapping("/order")
    @ApiOperation(value = "add order")
    public boolean createOrders(@RequestBody Order[] order) throws JsonProcessingException {
        orderService.saveOrder(order);
        return true;
    }

    @PutMapping("/status/{statusId}")
    public boolean updateOrders(@PathVariable String statusId, @RequestBody Order[] orders){
        System.out.println("status change");
        System.out.println(statusId);
        orderService.updateOrders(statusId, orders);
        return true;
    }
}
