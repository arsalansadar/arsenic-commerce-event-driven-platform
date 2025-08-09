package com.arsenic.notification_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDetails implements Serializable {

    private String orderId;
    private String userId;
    private String message;
    private Date creationDate;
    private Date deliveredItem;
    private List<String> itemList;
}
