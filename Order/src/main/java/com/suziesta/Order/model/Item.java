package com.suziesta.Order.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "items")
public class Item implements Serializable {
    @Id
    private Integer articleId;
    private String articleName;
    private Integer quantity;
    private double price;
    private Integer taxRate;

    @Column(name="order_id")
    private String orderId;
}
