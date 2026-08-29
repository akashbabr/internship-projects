package com.humancloud.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.humancloud.config.RabbitMQConfig;
import com.humancloud.dto.TaskAssignedEvent;

@Service
public class TaskProducer 
{

    private final RabbitTemplate rabbitTemplate;

    public TaskProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendTaskAssignedEvent(TaskAssignedEvent event) {

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                event);

        System.out.println("Message Sent : " + event);
    }
}