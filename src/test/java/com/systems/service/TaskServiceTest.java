package com.systems.service;

import com.systems.model.Task;
import com.systems.model.TaskType;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.core.StringRedisTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskServiceTest {

    @Autowired
    private TaskService taskService;

    @MockBean
    private StringRedisTemplate stringRedisTemplate;

    @MockBean
    private TaskDistributor taskDistributor;

    @Test
    public void testSubmitTask(){

        Task task = new Task(null, TaskType.EMAIL_SENDING, 1, null, "payload");
        Mockito.doNothing().when(stringRedisTemplate).opsForHash().put(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        Mockito.doNothing().when(taskDistributor).distributeTask(Mockito.anyString());String taskId = taskService.submitTask(task);

        assertNotNull(taskId);
        assertTrue(taskId.startsWith("EMAIL_SENDING-"));
    }

}
