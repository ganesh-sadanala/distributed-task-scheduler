package com.systems.model;

public class Task {
    private String id;
    private int priority;
    private String status;
    private String payload;

    public Task() {

    }

    public Task(String id, int priority, String status, String payload) {
        this.id = id;
        this.priority = priority;
        this.status = status;
        this.payload = payload;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", priority=" + priority +
                ", status='" + status + '\'' +
                ", payload='" + payload + '\'' +
                '}';
    }
}

