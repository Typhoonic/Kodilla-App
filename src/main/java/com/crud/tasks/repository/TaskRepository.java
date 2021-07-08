package com.crud.tasks.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {


    List<Task> findAll();

}
