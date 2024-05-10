package com.systems.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("EMAIL_SENDING")
public class EmailSendingExecutionStrategy implements TaskExecutionStrategy{

    private static final Logger logger= LoggerFactory.getLogger(EmailSendingExecutionStrategy.class);
    @Override
    public void executeTask(String taskId) {
        // Logic to execute email sending task
        try {
            logger.info("Email Sending task: {}", taskId);
        } catch (Exception e) {
            logger.error("Error executing Email Sending task: {}", taskId, e);
            throw new RuntimeException("Error executing Email Sending task", e);
        }

    }
}
