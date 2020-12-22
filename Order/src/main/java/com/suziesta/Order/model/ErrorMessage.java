package com.suziesta.Order.model;

import lombok.Data;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
public class ErrorMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private ZonedDateTime timestamp;
    private String message;

    public ErrorMessage(ZonedDateTime timestamp, String message) {
        this.timestamp=timestamp;
        this.message=message;
    }
}
