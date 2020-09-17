package com.suziesta.BulkOrder.model;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class Shipping implements Serializable {
    private String shipping_id;
    private String shipping_addressline1;
    private String shipping_addressline2;
    private String shipping_city;
    private String shipping_state;
    private String shipping_zip;
    private String shipping_method;

    public Shipping(){
        this.shipping_id = UUID.randomUUID().toString();
    }
}
