package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    void shouldFetchListOfTasks() throws Exception {
        //Given
        List<Task> tasks = List.of(new Task(1L, "title", "content"));
        List<TaskDto> tasksDto = List.of(new TaskDto(1L, "title", "content"));

        when(dbService.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(tasksDto);
        //When&Then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/task/getTasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("content")));
    }

    @Test
    void shouldFetchTask() throws Exception {
        //Given
        Optional<Task> optionalTask = Optional.of(new Task(2L, "title", "content"));
        TaskDto taskDto = new TaskDto(2L, "title", "content");

        when(dbService.getTaskById(anyLong())).thenReturn(optionalTask);
        when(taskMapper.mapToTaskDto(optionalTask.orElseThrow(IllegalArgumentException::new))).thenReturn(taskDto);
        //When&Then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/task/getTask")
                .contentType(MediaType.APPLICATION_JSON)
                        .param("taskId", "2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("content")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("title")));
    }

    @Test
    void shouldDeleteTask() throws Exception{
        //Given&When&Then
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/v1/task/deleteTask")
                .contentType(MediaType.APPLICATION_JSON)
                        .param("taskId", "2"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldUpdateTask() throws Exception {
        //Given
        Task task = new Task(2L, "title", "content");
        Task updateTask = new Task(2L, "title2", "content2");
        TaskDto taskDto = new TaskDto(2L, "title2", "content2");

        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(task);
        when(dbService.saveTask(any(Task.class))).thenReturn(updateTask);
        when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        //When&Then
        mockMvc.perform(MockMvcRequestBuilders
                .put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("title2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("content2")));
    }

    @Test
    void shouldCreateTask() throws Exception {
        //Given
        Task task = new Task(3L, "title", "content");
        TaskDto taskDto = new TaskDto(3L, "title", "content");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        //When&Then
        mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
