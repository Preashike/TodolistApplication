/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package practical.demo.services;

import java.util.List;
import org.springframework.http.ResponseEntity;
import practical.demo.dtos.request.ToDoListRequest;
import practical.demo.models.ToDoList;

/**
 *
 * @author Angel
 */
public interface TodoService {
    List<ToDoList> findAll();

    ToDoList createList(Long userId,ToDoListRequest myList);

    ResponseEntity<Object> findList(Long id);

    ResponseEntity<Object> update (Long id, ToDoListRequest request);

    void delete (Long id);
    
    ResponseEntity<List<ToDoList>> findUserTodo(Long userid);
}
