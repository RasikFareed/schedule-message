package com.rabbitmq.schedule;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/schedule")
public class ScheduleController {

    private final Publish publish;

    public ScheduleController(Publish publish) {
        this.publish = publish;
    }
    @PostMapping
    public void publishMessage(@RequestBody PublishMessage publishMessage) {
        publish.publishMessages(publishMessage);
    }

}
