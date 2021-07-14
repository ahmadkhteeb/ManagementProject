package com.training.managementProject.dto.mapper;


import com.training.managementProject.dto.model.TaskDto;
import com.training.managementProject.model.Task;

public class TaskMapper {

    // Map from task to taskDto
    public static TaskDto toTaskDto(Task task){
        return new TaskDto()
                .setId(task.getId())
                .setName(task.getName())
                .setStartTime(task.getStartTime())
                .setDeadline(task.getDeadline());
    }

    // Map from taskDto to task
    public static Task toTask(TaskDto taskDto){
        return new Task()
                .setId(taskDto.getId())
                .setName(taskDto.getName())
                .setStartTime(taskDto.getStartTime())
                .setDeadline(taskDto.getDeadline());
    }

}
