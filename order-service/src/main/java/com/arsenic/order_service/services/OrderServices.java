package com.arsenic.order_service.services;

import com.arsenic.order_service.config.RabbitMQConfig;
import com.arsenic.order_service.entity.OrderItemDetails;
import com.arsenic.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderServices {

    private final OrderRepository orderRepository;
    private final RabbitTemplate rabbitTemplate;


    public OrderItemDetails createOrder(List<String> item){
        OrderItemDetails orderItemDetails = new OrderItemDetails();
        orderItemDetails.setItemList(item);
        orderItemDetails.setOrderId(UUID.randomUUID().toString());
        orderItemDetails.setUserId("USER-"+new Random().nextInt(10000));
        orderItemDetails.setMessage("Order sent by xpress.com");
        orderItemDetails.setCreationDate(new Date());

        Calendar cal = Calendar.getInstance();
        cal.setTime(orderItemDetails.getCreationDate());
        cal.add(Calendar.DAY_OF_MONTH, 2);
        orderItemDetails.setDeliveredItem(cal.getTime());
        OrderItemDetails savedItem = orderRepository.save(orderItemDetails);

        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                savedItem);

        return savedItem;

    }
}
