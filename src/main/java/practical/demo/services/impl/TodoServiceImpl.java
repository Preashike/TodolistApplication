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
import practical.demo.services.TodoService;

/**
 *
 * @author Angel
 */

@Service
public class TodoServiceImpl implements TodoService {
    @Autowired
    private ToDoListRepository todolistRepository;


    @Override
    public List<ToDoList> findAll() {
        return todolistRepository.findAll();
    }

    @Override
    public ToDoList createList(Long userId, ToDoList myList) {
        Optional<ToDoList> optionaltodolist = todolistRepository.findById(myList.getId());
        if (optionaltodolist.isPresent()) {
            throw new EntityNotFoundException("Todo is already present");
        }

        //Validate the ID

        //Fetch User from the DB
        User user = new User();


        ToDoList todolist = new ToDoList();
        todolist.setUser(user);
        todolist.setTitle(myList.getTitle());
        return todolistRepository.save(myList);
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
    public void delete(Long id) {
        todolistRepository.deleteById(id);
    }
}
