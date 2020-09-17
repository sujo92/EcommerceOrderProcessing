package com.suziesta.BulkOrder.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name="orders")
public class Order implements Serializable {
    @Id
    @Column(name = "order_id")
    private String orderId;
    private Integer customerId;
    private String status;
    private double shipping_charges;
    private double total;
    private Timestamp createdDate;
    private Timestamp modifiedDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="order_id", referencedColumnName = "order_id")
    private List<Item> items;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="order_id", referencedColumnName = "order_id")
    private  List<Payments> payments;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="shipping_id")
    private Shipping shipping;

    public Order(){
        this.orderId = UUID.randomUUID().toString();
    }
}
