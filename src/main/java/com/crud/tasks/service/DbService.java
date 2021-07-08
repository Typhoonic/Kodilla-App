package com.crud.tasks.service;

import com.crud.tasks.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@AllArgsConstructor
public class DbService {

    private final TaskRepository repository;

    public List<Task> getAllTasks(){
        return repository.findAll();
    }

}
