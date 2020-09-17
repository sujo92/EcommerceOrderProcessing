package com.suziesta.BulkOrder.model;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
public class Order implements Serializable {
    private String orderId;
    private Integer customerId;
    private String status;
    private double shipping_charges;
    private double total;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private List<Item> items;
    private  List<Payments> payments;
    private Shipping shipping;

    public Order(){
        this.orderId = UUID.randomUUID().toString();
    }
}
