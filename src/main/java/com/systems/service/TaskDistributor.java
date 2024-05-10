package com.systems.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class TaskDistributor {

    private static final Logger logger = LoggerFactory.getLogger(TaskDistributor.class);
    private static final String TASK_CHANNEL = "task-channel";

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void distributeTask(String taskId) {
        // Publish task ID to Redis channel
        try {
            redisTemplate.convertAndSend(TASK_CHANNEL, taskId);
            logger.info("Task distributed successfully: {}", taskId);
        } catch (Exception e) {
            logger.error("Failed to distribute task: {}", taskId, e);
            throw new RuntimeException("Failed to distribute task", e);
        }
    }
}

