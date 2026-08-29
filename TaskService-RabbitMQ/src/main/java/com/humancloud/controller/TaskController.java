package com.humancloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.humancloud.dto.TaskAssignedEvent;
import com.humancloud.producer.TaskProducer;

@RestController
public class TaskController 
{
	@Autowired
    private  TaskProducer taskProducer;

    @PostMapping("/assign")
    public String assignTask(@RequestBody TaskAssignedEvent event) 
    {
        taskProducer.sendTaskAssignedEvent(event);
        return "Task Assigned Event Sent Successfully";
    }
}