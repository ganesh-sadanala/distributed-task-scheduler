package com.systems.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.core.StringRedisTemplate;

public class TaskDistributorTest {
    @Autowired
    private TaskDistributor taskDistributor;

    @MockBean
    private StringRedisTemplate redisTemplate;

    @Test
    public void testDistributeTask() {
        String taskId = "EMAIL_SENDING-12345";
        Mockito.doNothing().when(redisTemplate).convertAndSend(Mockito.anyString(), Mockito.anyString());

        taskDistributor.distributeTask(taskId);

        Mockito.verify(redisTemplate, Mockito.times(1)).convertAndSend("task-channel", taskId);
    }
}
