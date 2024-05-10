package com.systems.strategy;

public class Type2TaskExecutionStrategy implements TaskExecutionStrategy{
    @Override
    public void executeTask(String taskId) {
        // Logic to execute type 2 task
        System.out.println("Executing type 2 task: " + taskId);
    }
}
