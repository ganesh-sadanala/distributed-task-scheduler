package com.systems.service;

import com.systems.strategy.TaskExecutionStrategy;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

public class TaskExecutionServiceTest {

    @Autowired
    private TaskExecutionService taskExecutionService;

    @MockBean
    private Map<String, TaskExecutionStrategy> taskExecutionStrategies;

    @MockBean(name = "EMAIL_SENDING")
    private TaskExecutionStrategy emailSendingExecutionStrategy;

    @Test
    public void testExecuteTask() {
        String taskId = "EMAIL_SENDING-12345";
        Mockito.when(taskExecutionStrategies.get(eq("EMAIL_SENDING"))).thenReturn(emailSendingExecutionStrategy);
        Mockito.doNothing().when(emailSendingExecutionStrategy).executeTask(anyString());

        taskExecutionService.executeTask(taskId);

        Mockito.verify(emailSendingExecutionStrategy, Mockito.times(1)).executeTask(taskId);
    }
}
