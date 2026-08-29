package com.humancloud.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.humancloud.config.RabbitMQConfig;
import com.humancloud.dto.TaskAssignedEvent;

@Service
public class TaskConsumer {

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void receiveTask(TaskAssignedEvent event) 
    {

        System.out.println("==================================");
        System.out.println("New Task Assigned");
        System.out.println("Task Id      : " + event.getTaskId());
        System.out.println("Task Title   : " + event.getTaskTitle());
        System.out.println("Assigned To  : " + event.getAssignedUserName());
        System.out.println("Email        : " + event.getAssignedUserEmail());
        System.out.println("==================================");

    }
}