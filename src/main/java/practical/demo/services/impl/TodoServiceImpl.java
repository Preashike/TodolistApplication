/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practical.demo.services.impl;

import jakarta.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import practical.demo.dtos.request.ToDoListRequest;
import practical.demo.dtos.response.ApiResponse;
import practical.demo.models.ToDoList;
import practical.demo.models.User;
import practical.demo.repositories.ToDoListRepository;
import practical.demo.repositories.UserRepository;
import practical.demo.services.TodoService;

/**
 *
 * @author Angel
 */

@Service
public class TodoServiceImpl implements TodoService {
    @Autowired
    private ToDoListRepository todolistRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public List<ToDoList> findAll() {
        return todolistRepository.findAll();
    }

    @Override
    public ToDoList createList(Long userId, ToDoListRequest myList) {
        Optional<ToDoList> optionaltodolist = todolistRepository.findByTitle(myList.getTitle());
        if (optionaltodolist.isPresent()) {
            throw new EntityNotFoundException("Title is already present");
        }

        //Validate the ID
        Optional<User> optionaluser = userRepository.findById(userId);
        if (optionaluser.isEmpty()){
            throw new EntityNotFoundException("UserId doesn't exist");
        }
       
        ToDoList todolist = new ToDoList();
        todolist.setUser(optionaluser.get());
        todolist.setTitle(myList.getTitle());
        return todolistRepository.save(todolist);
    }

    @Override
    public ResponseEntity<Object> findList(Long id) {

        Map<String, Object> responseBody = new HashMap<>();

        Optional<ToDoList> optionallist = todolistRepository.findById(id);
        if (optionallist.isEmpty()) {
            responseBody.put("message", "User Not Found");
            responseBody.put("data", null);
            return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
        }
        ToDoList list = optionallist.get();
        responseBody.put("message", "Successful");
        responseBody.put("data", list);
        return ResponseEntity.ok(responseBody);

    }


    @Override
    public ResponseEntity<Object> update(Long id, ToDoListRequest request) {
        Optional<ToDoList> optionallist = todolistRepository.findById(id);
        ApiResponse responseBody = new ApiResponse();
        if (optionallist.isEmpty()) {
            responseBody.setMessage("Invalid User");
            responseBody.setStatus(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity(responseBody, HttpStatus.NOT_FOUND);
        }
        Optional<ToDoList> optionaltitle = todolistRepository.findByTitle(request.getTitle());
        if (optionaltitle.isPresent()) {
            ToDoList list = optionaltitle.get();
            if (list.getId() != id) {
                return new ResponseEntity("User Already Exist", HttpStatus.BAD_REQUEST);
            }
        }
        ToDoList lists = optionallist.get();
        lists.setTitle(request.getTitle());
        todolistRepository.save(lists);
        return ResponseEntity.ok("Successful");

    }
    
    @Override
    public ResponseEntity<List<ToDoList>> findUserTodo(Long userid){
        Optional<User> optionaluser =userRepository.findById(userid);
        ApiResponse responseBody = new ApiResponse();
        if (optionaluser.isEmpty()) {
            responseBody.setMessage("Invalid User");
            responseBody.setStatus(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity(responseBody, HttpStatus.NOT_FOUND);
        }
        List<ToDoList> toDolist = todolistRepository.findByUser(optionaluser.get());
            responseBody.setMessage("Successful");
            responseBody.setData(toDolist);
            return new ResponseEntity(responseBody, HttpStatus.OK);   
    }

    @Override
    public void delete(Long id) {
        todolistRepository.deleteById(id);
    }
}
