package com.rabbitmq.schedule;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Service
public class Publish {

    @Value("${app.receiver.exchange}")
    private String exchange;
    @Value("${app.receiver.routingKey}")
    private String routingKey;

    private AmqpTemplate rabbitTemplate;

    public Publish(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishMessages(PublishMessage publishMessage) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Published Time: "+dtf.format(now));
        rabbitTemplate.convertAndSend(exchange,
                routingKey,
                publishMessage.getMessage(),
                new CustomMessagePostProcessor(publishMessage.getTtl()));
    }
}
