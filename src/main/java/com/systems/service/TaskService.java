package com.systems.service;

import com.systems.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    TaskDistributor taskDistributor;

    public String submitTask(Task task) {
        String taskId = task.getType() + "-" + UUID.randomUUID().toString();
        String taskKey = "task:" + taskId;

        try {
            stringRedisTemplate.opsForHash().put(taskKey, "priority", String.valueOf(task.getPriority()));
            stringRedisTemplate.opsForHash().put(taskKey, "payload", task.getPayload());

            taskDistributor.distributeTask(taskId);

            logger.info("Task submitted successfully with ID: {}", taskId);
        } catch (Exception e) {
            logger.error("Failed to submit task", e);
            throw new RuntimeException("Failed to submit task", e);
        }

        return taskId;
    }
}



