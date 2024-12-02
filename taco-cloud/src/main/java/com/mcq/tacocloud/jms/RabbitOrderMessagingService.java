package com.mcq.tacocloud.jms;

import com.mcq.tacocloud.model.TacoOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitOrderMessagingService {

    private final RabbitTemplate rabbit;

    public void sendOrder(TacoOrder order) {
        rabbit.convertAndSend("tacocloud.order", "order", order,
                message -> {
                    MessageProperties props = message.getMessageProperties();
                    props.setHeader("X_ORDER_SOURCE", "WEB");
                    return message;
                });
    }
}
