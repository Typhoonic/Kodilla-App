package com.crud.tasks.repository;

import com.crud.tasks.domain.Task;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {


}
