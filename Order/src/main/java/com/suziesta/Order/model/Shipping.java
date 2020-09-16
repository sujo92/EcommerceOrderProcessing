package com.suziesta.Order.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
public class Shipping implements Serializable {
    @Id
    @Column(name="shipping_id")
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
