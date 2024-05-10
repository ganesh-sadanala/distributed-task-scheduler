package com.systems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class TaskDistributor {

    private static final String TASK_CHANNEL = "task-channel";

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void distributeTask(String taskId) {
        // Publish task ID to Redis channel
        redisTemplate.convertAndSend(TASK_CHANNEL, taskId);
    }
}

