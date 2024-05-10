package com.systems.service;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class TaskSubscriber implements MessageListener {

    private static final String TASK_CHANNEL = "task-channel";

    @Override
    public void onMessage(Message message, byte[] pattern) {
        // Receive task ID from Redis channel
        String taskId = new String(message.getBody());
        System.out.println("Received task ID: " + taskId);

        // process the task
    }
}

