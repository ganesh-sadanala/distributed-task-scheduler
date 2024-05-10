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

    public String submitTask(Task task) {
        String taskId = UUID.randomUUID().toString();
        String taskKey = "task:" + taskId;

        stringRedisTemplate.opsForHash().put(taskKey, "priority", String.valueOf(task.getPriority()));
        stringRedisTemplate.opsForHash().put(taskKey, "payload", task.getPayload());

        return taskId;
    }
}



