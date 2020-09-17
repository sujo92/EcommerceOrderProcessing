package com.suziesta.Order.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "items")
public class Item implements Serializable {
    @Id
    @Column(name="order_item_id")
    private String id;
    private Integer item_id;
    private String item_name;
    private Integer item_qty;
    private double subtotal;
    private Integer tax;

    @Column(name="order_id")
    private String orderId;

    public Item(){
        this.id = UUID.randomUUID().toString();
    }
}
