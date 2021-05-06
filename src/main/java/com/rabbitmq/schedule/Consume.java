package com.rabbitmq.schedule;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class Consume {

    @RabbitListener(queues = "${app.consumer.queue}")
    private void listen(String message) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Consumed time: "+dtf.format(now));
        System.out.println("Incoming Message. message="+ message);
    }
}
