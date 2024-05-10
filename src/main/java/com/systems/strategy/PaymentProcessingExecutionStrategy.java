package com.systems.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("PAYMENT_PROCESSING")
public class PaymentProcessingExecutionStrategy implements TaskExecutionStrategy{

    private static final Logger logger = LoggerFactory.getLogger(PaymentProcessingExecutionStrategy.class);
    @Override
    public void executeTask(String taskId) {
        // Logic to execute payment processing task
        try {
            logger.info("Payment Processing task: {}", taskId);
        } catch (Exception e) {
            logger.error("Error executing Payment Processing task: {}", taskId, e);
            throw new RuntimeException("Error executing Payment Processing task", e);
        }
    }
}
