package com.humancloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskAssignedEvent 
{

    private Long taskId;

    private String taskTitle;

    private Long assignedUserId;

    private String assignedUserName;

    private String assignedUserEmail;
}