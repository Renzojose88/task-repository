package com.app.taskmicroservice.service;


import com.app.taskmicroservice.controller.TaskController;
import com.app.taskmicroservice.entity.Task;
import com.app.taskmicroservice.exceptions.ToDoException;
import com.app.taskmicroservice.repository.TaskRepository;
import org.aspectj.bridge.IMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.TooManyListenersException;

@Service
public class TaskService {

    private static Logger logger = LoggerFactory.getLogger(TaskController.class);
    @Autowired //es una dependencia que debe usarse en esta clase
    TaskRepository taskRepository;

    public Task addTask(Task taskRequest){
        logger.info("Init Service addTutorial");
        taskRequest.setCreateDate(LocalDateTime.now());
        taskRequest.setActive(true);
        logger.info("Fin Service addTutorial");
       Task task = taskRepository.save(taskRequest);
       return task;
    }

    public List<Task> getTaskAll(){
        return taskRepository.findAll();
    }

    public void deleteTask(Long idTask){
        taskRepository.deleteById(idTask);
    }

    public Optional<Task> getTaskById(Long idTask){
        return taskRepository.findById(idTask);
    }

    public Task updateTask(Long idTask, Task taskUpdate){
        Optional<Task> task = taskRepository.findById(idTask);
        if(task.isEmpty()){
            throw new ToDoException("Task no found", HttpStatus.NOT_FOUND);
        }
        taskUpdate.setId(idTask);
        taskUpdate.setCreateDate(task.get().getCreateDate());
        taskUpdate.setActive(task.get().isActive());
        return taskRepository.save(taskUpdate);
    }

}
