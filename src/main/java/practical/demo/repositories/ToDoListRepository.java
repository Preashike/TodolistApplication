/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package practical.demo.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import practical.demo.models.ToDoList;

/**
 *
 * @author Angel
 */
public interface ToDoListRepository extends JpaRepository<ToDoList,Long>{
    Optional<ToDoList>findByTitle(String title);
    Optional<ToDoList>findByDone(boolean done);
}
