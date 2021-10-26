package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TaskController {

    private final DbService dbService;
    private final TaskMapper taskMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/tasks")
    public List<TaskDto> getTasks(){
        List<Task> taskList = dbService.getAllTasks();
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);
        return taskDtoList;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tasks/{taskId}")
    public TaskDto getTask(@PathVariable Long taskId) {
        Optional<Task> taskListById = dbService.getTaskById(taskId);

        TaskDto taskDto = taskMapper.mapToTaskDto(taskListById.orElseThrow(IllegalArgumentException::new));

        return taskDto;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/tasks", consumes = APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody TaskDto taskDto){
        Task task = taskMapper.mapToTask(taskDto);
        dbService.saveTask(task);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/tasks")
    public TaskDto updateTask(@RequestBody TaskDto taskDto){
        Task task = taskMapper.mapToTask(taskDto);
        Task savedTask = dbService.saveTask(task);

        return taskMapper.mapToTaskDto(savedTask);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/tasks/{taskId}")
    public void deleteTask(@PathVariable Long taskId){
        try {
            dbService.deleteTask(taskId);
        }catch(NoSuchElementException e){
            e.getStackTrace();
        }
    }
}
