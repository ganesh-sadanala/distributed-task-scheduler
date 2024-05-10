package com.systems.service;

import com.systems.model.Task;
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

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    TaskDistributor taskDistributor;

    public String submitTask(Task task) {
        String taskId = task.getType() + "-" + UUID.randomUUID().toString();
        String taskKey = "task:" + taskId;

        stringRedisTemplate.opsForHash().put(taskKey, "priority", String.valueOf(task.getPriority()));
        stringRedisTemplate.opsForHash().put(taskKey, "payload", task.getPayload());

        // After storing the task in Redis, call TaskDistributor to distribute the task
        taskDistributor.distributeTask(taskId);

        return taskId;
    }
}



