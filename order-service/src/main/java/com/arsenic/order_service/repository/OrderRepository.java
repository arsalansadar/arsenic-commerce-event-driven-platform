package com.arsenic.order_service.repository;

import com.arsenic.order_service.entity.OrderItemDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<OrderItemDetails, String> {
    List<OrderItemDetails> findByUserId(String userId);
}
