package com.blueprint.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blueprint.backend.entity.Task;
import com.blueprint.backend.repository.TaskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }
    public Task getTaskById(Long id){
        return taskRepository.findById(id).orElse(null);
    }
    public Task createTask(Task task){
        return taskRepository.save(task);
    }
    public Task updateTask(Long id ,Task task){
        task.setId(id);
        return taskRepository.save(task);
    }
    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }
}
