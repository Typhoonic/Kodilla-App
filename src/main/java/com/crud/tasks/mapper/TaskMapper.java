package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task mapToTask(TaskDto taskDto);
    TaskDto mapToTaskDto(Task task);
    List<TaskDto> mapToTaskDtoList(List<Task> taskList);
}
