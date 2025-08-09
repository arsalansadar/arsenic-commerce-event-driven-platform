package com.arsenic.order_service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "orders")
public class OrderItemDetails implements Serializable {

    @Id
    private String orderId;
    private String userId;
    private String message;
    private Date creationDate;
    private Date deliveredItem;
    private List<String> itemList;
}
