package com.systems.strategy;

import org.springframework.stereotype.Component;

@Component("EMAIL_SENDING")
public class EmailSendingExecutionStrategy implements TaskExecutionStrategy{
    @Override
    public void executeTask(String taskId) {
        // Logic to execute type 1 task
        System.out.println("Email Sending task: " + taskId);
    }
}
