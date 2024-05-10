package com.systems.service;

import com.systems.strategy.TaskExecutionStrategy;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TaskExecutionService {
    private static final Logger logger = LoggerFactory.getLogger(TaskExecutionService.class);

    @Autowired
    private Map<String, TaskExecutionStrategy> taskExecutionStrategies;

    @CircuitBreaker(name = "taskExecution", fallbackMethod = "handleTaskExecutionFailure")
    public void executeTask(String taskId) {
        // Execute task logic here
        logger.info("Executing task: {}", taskId);

        // Extract task type from taskId (assuming taskId format: typeX-taskId)
        String taskType = taskId.split("-")[0];

        // process the task
        // Get appropriate strategy based on task type and execute task
        TaskExecutionStrategy strategy = taskExecutionStrategies.get(taskType);
        if (strategy != null) {
            try {
                strategy.executeTask(taskId);

                // Simulate task failure
                if (Math.random() < 0.5) {
                    throw new RuntimeException("Task execution failed for task: " + taskId);
                }
            } catch (Exception e) {
                // Handle task execution failure
                logger.error("Failed to execute task: {}", taskId, e);
            }
        } else {
            // Handle unknown task type
            logger.warn("Unknown task type: {}", taskType);
        }
    }

    // Fallback method to handle task execution failures
    public void handleTaskExecutionFailure(String taskId, Throwable throwable) {
        // Log or perform other actions to handle task execution failure
        System.out.println("Task execution failed for task: " + taskId);
    }
}

