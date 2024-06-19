package com.systems.controller;

import com.systems.model.Task;
import com.systems.model.TaskType;
import com.systems.service.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
@AutoConfigureMockMvc
public class TaskControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TaskService taskService;

    @Test
    public void testSubmitTask() throws Exception{
        Task task = new Task(null, TaskType.EMAIL_SENDING, 1, null, "payload");
        String taskId = "EMAIL-SENDING-123";

        Mockito.when(taskService.submitTask(Mockito.any(Task.class))).thenReturn(taskId);

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"type\":\"EMAIL_SENDING\",\"priority\":1,\"payload\":\"payload\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Task submitted successfully with ID: " + taskId));

    }

}
