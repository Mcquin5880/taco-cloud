package com.mcq.tacocloud.service;

import com.mcq.tacocloud.jms.RabbitOrderMessagingService;
import com.mcq.tacocloud.model.TacoOrder;
import com.mcq.tacocloud.repo.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final RabbitOrderMessagingService messagingService;

    public void processOrder(TacoOrder order) {
        orderRepository.save(order);
        messagingService.sendOrder(order);
    }
}
