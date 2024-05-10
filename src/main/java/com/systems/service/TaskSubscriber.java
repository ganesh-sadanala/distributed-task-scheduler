package com.systems.service;

import com.systems.strategy.TaskExecutionStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TaskSubscriber implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(TaskSubscriber.class);

    private static final String TASK_CHANNEL = "task-channel";

    @Autowired
    private TaskExecutionService taskExecutionService;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        // Receive task ID from Redis channel
        String taskId = new String(message.getBody());
        logger.info("Received task ID: {}", taskId);

        // Execute task using TaskExecutionService with Circuit Breaker
        try {
            taskExecutionService.executeTask(taskId);
        } catch (Exception e) {
            logger.error("Error processing task: {}", taskId, e);
        }
    }
}

