package com.systems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.util.Properties;

@SpringBootApplication
public class DistributedTaskScheduler implements CommandLineRunner {

    @Autowired
    private RedisConnectionFactory connectionFactory;

    public static void main(String[] args) {
        SpringApplication.run(DistributedTaskScheduler.class);
    }

    @Override
    public void run(String... args) throws Exception {
        try (RedisConnection connection = connectionFactory.getConnection()) {
            // Get the server info
            Properties serverInfo = connection.info();
            System.out.println("Connected to Redis cluster successfully!");
        } catch (Exception e) {
            System.out.println("Failed to connect to Redis cluster: " + e.getMessage());
        }
    }
}
