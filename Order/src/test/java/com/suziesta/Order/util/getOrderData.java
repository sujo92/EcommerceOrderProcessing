package com.suziesta.Order.util;

import com.suziesta.Order.model.Item;
import com.suziesta.Order.model.Order;
import com.suziesta.Order.model.Payments;
import com.suziesta.Order.model.Shipping;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;

public class getOrderData {
    public static Order getOrder(){
        Order o = new Order();

        o.setOrderId("0ccf443c-c7d5-4c14-90e4-3133283a4ed7");
        o.setCustomerId(7);
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        o.setCreatedDate(timestamp);
        o.setModifiedDate(timestamp);
        o.setShipping_charges(20);
        o.setTotal(500);
        o.setStatus("packed");

        Item i = new Item();
        i.setId("d595199b-2fa8-44ca-a674-103a507ed1b8");
        i.setItem_id(70);
        i.setItem_name("brush");
        i.setItem_qty(2);
        i.setSubtotal(34.99);
        i.setTax(19);

        o.setItems(Arrays.asList(i));
        Payments p = new Payments();
        p.setPaymentId("0002b7c0-5306-4851-a61c-f7324fc77035");
        p.setBilling_addressline1("302 tampa beach street");
        p.setBilling_addressline2("apt-25");
        p.setCity("Tampa");
        p.setPayment_confirmation_number(150);
        p.setPayment_date(timestamp);
        p.setPayment_method("Credit card");
        p.setState("FL");
        p.setZip(56125);

        o.setPayments(Arrays.asList(p));

        Shipping s = new Shipping();
        s.setShipping_id("13f35a4e-ae5d-41c5-bba6-d37348ad503a");
        s.setShipping_addressline1("302 tampa beach street");
        s.setShipping_addressline2("apt-25");
        s.setShipping_city("Tampa");
        s.setShipping_method("home delievery");
        s.setShipping_state("FL");
        s.setShipping_zip("56125");

        o.setShipping(s);

        return o;
    }

    public static Order getOrder2(){
        Order o = new Order();

        o.setOrderId("3ce83299-af71-4bed-a6d3-898d7d9c3dbf");
        o.setCustomerId(10);
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        o.setCreatedDate(timestamp);
        o.setModifiedDate(timestamp);
        o.setShipping_charges(25);
        o.setTotal(1000);
        o.setStatus("created");

        Item i = new Item();
        i.setId("178558c8-0a85-41a0-b6ad-8883cca5e094");
        i.setItem_id(70);
        i.setItem_name("brush");
        i.setItem_qty(2);
        i.setSubtotal(34.99);
        i.setTax(19);

        o.setItems(Arrays.asList(i));
        Payments p = new Payments();
        p.setPaymentId("2d1ecd62-12a5-47a9-b12c-010adf1739b1");
        p.setBilling_addressline1("302 tampa beach street");
        p.setBilling_addressline2("apt-25");
        p.setCity("Tampa");
        p.setPayment_confirmation_number(150);
        p.setPayment_date(timestamp);
        p.setPayment_method("Credit card");
        p.setState("FL");
        p.setZip(56125);

        o.setPayments(Arrays.asList(p));

        Shipping s = new Shipping();
        s.setShipping_id("340adaf8-bd0f-4b32-85cf-7d8a61e36e2a");
        s.setShipping_addressline1("302 tampa beach street");
        s.setShipping_addressline2("apt-25");
        s.setShipping_city("Tampa");
        s.setShipping_method("home delievery");
        s.setShipping_state("FL");
        s.setShipping_zip("56125");

        o.setShipping(s);

        return o;
    }
}
