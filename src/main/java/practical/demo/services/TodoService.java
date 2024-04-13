package practical.demo.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import practical.demo.dtos.request.ToDoListRequest;
import practical.demo.models.ToDoList;

import java.util.List;

public interface TodoService {

    List<ToDoList> findAll();

    ToDoList createList(Long userId,ToDoList myList);

    ResponseEntity<Object> findList(Long id);

    ResponseEntity<Object> update (Long id, ToDoListRequest request);

    void delete (Long id);


}
