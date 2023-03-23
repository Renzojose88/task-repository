package com.app.taskmicroservice.service;

import com.app.taskmicroservice.entity.Task;
import com.app.taskmicroservice.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class TaskServiceTest {

    @Mock
    TaskRepository taskRepository;

    @InjectMocks
    TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void test01(){
        List<String> mockList = Mockito.mock(List.class);
        Mockito.when(mockList.size()).thenReturn( 5);
        assertTrue(mockList.size()==5);
    }

    @Test
    void addTask() {

    }

    @Test
    void getTaskAll() {
        Mockito.when(taskRepository.findAll()).thenReturn(getTask());
        var result = taskService.getTaskAll();
        assertEquals(1,result.size());
    }
    private List<Task> getTask(){
        List<Task> listTask = new ArrayList<>();
        Task task = new Task();
        task.setActive(true);
        task.setId(15L);
        task.setCodetask("prueba");
        listTask.add(task);
        return listTask;
    }

    private Task getTaskId(){
        //List<Task> listTask = new ArrayList<>();
        Task task = new Task();
        task.setActive(true);
        task.setId(15L);
        task.setCodetask("renzo");
        //listTask.add(task);
        return task;
    }

    @Test
    void deleteTask() {
    }

    @Test
    void getTaskById() {
        Long id = 125L;
        Mockito.when(taskRepository.findById(any())).thenReturn(Optional.of(getTaskId()));
        var result = taskService.getTaskById(id);
        assertEquals("renzo",result.get().getCodetask());
    }

    @Test
    void updateTask() {
    }
}