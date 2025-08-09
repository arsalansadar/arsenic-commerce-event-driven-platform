package com.arsenic.order_service.controller;


import com.arsenic.order_service.entity.OrderItemDetails;
import com.arsenic.order_service.services.OrderServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderServices orderServices;

    @PostMapping("/createOrder")
    public ResponseEntity<OrderItemDetails> createOrder(@RequestBody List<String> item) {
        System.out.println("OrderRequest: "+item);
        OrderItemDetails order = orderServices.createOrder(item);
        return new ResponseEntity<>(order, HttpStatus.CREATED);

    }


    @PostMapping
    public String testRabbitMQImplementation(@RequestParam String orderId, @RequestParam String email) {
        //publisher.publishOrderCreated(orderId, email);
        return "Order created and event published!";
    }




}
