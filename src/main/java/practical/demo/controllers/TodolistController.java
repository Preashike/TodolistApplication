/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practical.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practical.demo.dtos.request.ToDoListRequest;
import practical.demo.models.ToDoList;
import practical.demo.services.TodoService;

import java.util.List;

/**
 * @author Angel
 */
@RestController
@RequestMapping("todolist")
public class TodolistController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/lists")
    public List<ToDoList> findAll() {
        return  todoService.findAll();
    }

    @PostMapping("{userid}/create")
    public ToDoList createList(@PathVariable Long userId,@RequestBody ToDoList mylist) {
        return todoService.createList(userId,mylist);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody ToDoListRequest request) {
       return todoService.update(id, request);
    }

    @GetMapping("/lists/{id}")
    public ResponseEntity<Object> findlist(@PathVariable Long id) {
      return   todoService.findList(id);
    }

    @DeleteMapping("/soft-delete/{id}")
    public void delete(@PathVariable Long id) {
        todoService.delete(id);
    }
}
