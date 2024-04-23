/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practical.demo.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import practical.demo.dtos.request.ToDoListRequest;
import practical.demo.models.ToDoList;
import practical.demo.services.TodoService;

/**
 *
 * @author Angel
 */
@RestController
@RequestMapping("todolist")
public class TodolistController {
    
    @Autowired
    private TodoService todoService;
            
    @GetMapping("/lists")
    public List<ToDoList> findAll(){
        return todoService.findAll();
    }
    
    @PostMapping("/{userid}/create")
    public ToDoList createList(@PathVariable Long userid,@RequestBody ToDoListRequest mylist) {
        return todoService.createList(userid,mylist);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody ToDoListRequest request) {
       return todoService.update(id, request);
    }
    
    @GetMapping("/lists/{id}")
    public ResponseEntity<Object> findlist(@PathVariable Long id) {
      return todoService.findList(id);
    }   
    
    @GetMapping("/user/{userid}")
    public ResponseEntity<List<ToDoList>> findUserTodo(@PathVariable Long userid){
        return todoService.findUserTodo(userid);
    }
    
    @DeleteMapping("/soft-delete/{id}")
    public void delete(@PathVariable Long id) {
        todoService.delete(id);
    }
}
