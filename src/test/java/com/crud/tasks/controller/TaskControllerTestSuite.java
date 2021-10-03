package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTestSuite {

    @InjectMocks
    private TaskController taskController;

    @Mock
    private DbService dbService;

    @Mock
    private TaskMapper taskMapper;

    @Test
    void shouldReturnListOfTasks(){
        //Given
        List<Task> tasks =
                List.of(new Task(0L, "name", "description"));

        List<TaskDto> tasksDto =
                List.of(new TaskDto(0L, "name", "description"));

        when(dbService.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(tasksDto);
        //When
        List<TaskDto> returnedTasksList = taskController.getTasks();
        //Then
        assertThat(returnedTasksList).isNotNull();
        assertThat(returnedTasksList.size()).isEqualTo(1);
    }

    @Test
    void shouldReturnTask(){
        //Given
        Optional<Task> taskOptional = Optional.of(new Task(0L, "name", "description"));
        TaskDto taskDto = new TaskDto(0L, "name", "description");

        when(dbService.getTaskById(anyLong())).thenReturn(taskOptional);
        when(taskMapper.mapToTaskDto(taskOptional.orElseThrow(IllegalArgumentException::new))).thenReturn(taskDto);
        //When
        TaskDto returnedTask = taskController.getTask(0L);
        //Then
        assertThat(returnedTask).isNotNull();
        assertEquals(0L, returnedTask.getId());
    }

    @Test
    void shouldUpdateTask(){
        //Given
        TaskDto taskDto = new TaskDto(0L, "name", "description");
        Task task = new Task(0L, "name", "description");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(dbService.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        //When
        TaskDto returnedTaskDto = taskController.updateTask(taskDto);
        //Then
        assertThat(returnedTaskDto).isNotNull();
        assertEquals("name", returnedTaskDto.getTitle());
    }
}
