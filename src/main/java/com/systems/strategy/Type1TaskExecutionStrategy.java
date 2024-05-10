package com.systems.strategy;

public class Type1TaskExecutionStrategy implements TaskExecutionStrategy{
    @Override
    public void executeTask(String taskId) {
        // Logic to execute type 1 task
        System.out.println("Executing type 1 task: " + taskId);
    }
}
