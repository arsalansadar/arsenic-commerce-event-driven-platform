package com.arsenic.notification_service.services;

import com.arsenic.notification_service.dto.OrderItemDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEventListener {

    @RabbitListener(queues = "order_notifications")
    public void consumeOrder(OrderItemDetails orderItemDetails) throws JsonProcessingException {
        System.out.println("📩 Received Order: " + orderItemDetails.getOrderId());


        sendEmail(orderItemDetails);
        sendSMS(orderItemDetails);
    }

    private void sendEmail(OrderItemDetails orderItemDetails) {
        System.out.println("📧 Email sent to user " + orderItemDetails.getUserId() +
                " for Order ID " + orderItemDetails.getOrderId() +
                ","+orderItemDetails.getMessage()+
                ", order placed by "+orderItemDetails.getCreationDate()+
                ", order expected deliever "+ orderItemDetails.getDeliveredItem());
    }

    private void sendSMS(OrderItemDetails orderItemDetails) {
        System.out.println("📱 SMS sent to user " + orderItemDetails.getUserId() +
                " for Order ID " + orderItemDetails.getOrderId());
    }

}
