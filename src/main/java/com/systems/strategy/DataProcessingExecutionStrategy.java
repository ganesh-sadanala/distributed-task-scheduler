package com.systems.strategy;

import org.springframework.stereotype.Component;

@Component("DATA_PROCESSING")
public class DataProcessingExecutionStrategy implements TaskExecutionStrategy{
    @Override
    public void executeTask(String taskId) {
        // Logic to execute type 2 task
        System.out.println("Data Processing task: " + taskId);
    }
}
