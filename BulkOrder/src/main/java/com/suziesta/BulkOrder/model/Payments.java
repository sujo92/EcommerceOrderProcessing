package com.suziesta.BulkOrder.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class Payments{
    private String paymentId;
    private String payment_method;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone="Europe/Berlin")
    private Timestamp payment_date;
    private Integer payment_confirmation_number;
    private String billing_addressline1;
    private String billing_addressline2;
    private String city;
    private String state;
    private Integer zip;

    private String orderId;

    public Payments(){
        this.paymentId = UUID.randomUUID().toString();
    }
}
