package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/v1/task")
@RequiredArgsConstructor
public class TaskController {

    private final DbService dbService;
    private final TaskMapper taskMapper;


    @RequestMapping(method = RequestMethod.GET, value = "getTasks")//endpoint
    public List<TaskDto> getTasks(){
        List<Task> taskList = dbService.getAllTasks();
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);
        return taskDtoList;
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTask")
    public TaskDto getTask(Long taskId) {
        Optional<Task> taskListById = dbService.getTaskById(1L);
        TaskDto task = null;

        try{
            task = taskMapper.mapToTaskDto(taskListById.get());
        }catch(NoSuchElementException e){
            e.getStackTrace();
        }

        return task;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteTask")
    public void deleteTask(Long taskId){

    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateTask")
    public TaskDto updateTask(TaskDto task){
        return new TaskDto(1L, "Edited test title", "Test_Content");
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTask")
    public void createTask(TaskDto task){

    }

}
