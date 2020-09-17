package com.suziesta.BulkOrder.model;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class Item implements Serializable {
    private String id;
    private Integer item_id;
    private String item_name;
    private Integer item_qty;
    private double subtotal;
    private double tax;

    private String orderId;

    public Item(){
        this.id = UUID.randomUUID().toString();
    }
}
