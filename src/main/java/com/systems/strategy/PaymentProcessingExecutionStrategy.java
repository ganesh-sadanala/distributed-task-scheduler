package com.systems.strategy;

import org.springframework.stereotype.Component;

@Component("PAYMENT_PROCESSING")
public class PaymentProcessingExecutionStrategy implements TaskExecutionStrategy{
    @Override
    public void executeTask(String taskId) {
        // Logic to execute
        System.out.println("Payment Processing task: " + taskId);
    }
}
