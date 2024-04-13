/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practical.demo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import practical.demo.dtos.response.ApiResponse;
import practical.demo.models.ToDoList;
import practical.demo.repositories.ToDoListRepository;

/**
 *
 * @author Angel
 */
@RestController
@RequestMapping("api/todolist")
public class TodolistController {
    
    @Autowired
    private ToDoListRepository todolistRepository;
            
    @GetMapping("/lists")
    public List<ToDoList> findAll(){
        return todolistRepository.findAll();
    }
    @PostMapping("/create")
    public String createList(@RequestBody ToDoList mylist){
        System.out.println(mylist);
        
        Optional<ToDoList> optionaltodolist = todolistRepository.findById(mylist.getId());
        if (optionaltodolist.isPresent()){
            return "Item already on your List";
        }
        ToDoList todolist = new ToDoList();
        todolist.setTitle(mylist.getTitle());
        todolistRepository.save(mylist);
        return "Successully";
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id,@RequestBody ToDoListRequest request){
        Optional<ToDoList> optionallist= todolistRepository.findById(id);
        ApiResponse responseBody = new ApiResponse();
        if (optionallist.isEmpty()){
            responseBody.setMessage("Invalid User");
            responseBody.setStatus(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity(responseBody,HttpStatus.NOT_FOUND);
        }
        Optional<ToDoList> optionaltitle= todolistRepository.findByTitle(request.getTitle());
        if(optionaltitle.isPresent()){
            ToDoList list =optionaltitle.get();
            if (list.getId()!=id){
                return new ResponseEntity("User Already Exist",HttpStatus.BAD_REQUEST);
            }
        }
        ToDoList lists = optionallist.get();
            lists.setTitle(request.getTitle());
            todolistRepository.save(lists);
            return ResponseEntity.ok("Successful");
        }
    @GetMapping("/lists/{id}")
    public ResponseEntity<Object> findlist(@PathVariable Long id){
        Optional<ToDoList> optionallist= todolistRepository.findById(id);
        Map<String,Object>responseBody=new HashMap<>();
        if(optionallist.isEmpty()){
            responseBody.put("message","User Not Found");
            responseBody.put("data",null);
            return new 
        ResponseEntity<>(responseBody,HttpStatus.NOT_FOUND);
        }
        ToDoList list =optionallist.get();
        responseBody.put("message","Successful");
        responseBody.put("data",list);
        return ResponseEntity.ok(responseBody);
    }
    
    @DeleteMapping("/soft-delete/{id}")
    public void delete(@PathVariable Long id){
        todolistRepository.deleteById(id);
    }
}
