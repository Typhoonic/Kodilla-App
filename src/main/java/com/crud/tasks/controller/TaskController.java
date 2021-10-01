package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/v1/task/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TaskController {

    private final DbService dbService;
    private final TaskMapper taskMapper;


    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
    public List<TaskDto> getTasks(){
        List<Task> taskList = dbService.getAllTasks();
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);
        return taskDtoList;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTask")
    public TaskDto getTask(@RequestParam Long taskId) {
        Optional<Task> taskListById = dbService.getTaskById(taskId);

        TaskDto taskDto = taskMapper.mapToTaskDto(taskListById.orElseThrow(IllegalArgumentException::new));

        return taskDto;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteTask")
    public void deleteTask(@RequestParam Long taskId){
        try {
            dbService.deleteTask(taskId);
        }catch(NoSuchElementException e){
            e.getStackTrace();
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateTask")
    public TaskDto updateTask(@RequestBody TaskDto taskDto){
        Task task = taskMapper.mapToTask(taskDto);
        Task savedTask = dbService.saveTask(task);

        return taskMapper.mapToTaskDto(savedTask);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTask")
    public void createTask(@RequestBody TaskDto taskDto){
        Task task = taskMapper.mapToTask(taskDto);
        dbService.saveTask(task);
    }

}
