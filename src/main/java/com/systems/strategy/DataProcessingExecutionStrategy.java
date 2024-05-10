package com.systems.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("DATA_PROCESSING")
public class DataProcessingExecutionStrategy implements TaskExecutionStrategy{

    private static final Logger logger = LoggerFactory.getLogger(DataProcessingExecutionStrategy.class);
    @Override
    public void executeTask(String taskId) {
        try {
            // Logic to execute data processing task
            logger.info("Data Processing task: {}", taskId);
        } catch (Exception e) {
            logger.error("Error executing Data Processing task: {}", taskId, e);
            throw new RuntimeException("Error executing Data Processing task", e);
        }
    }
}
