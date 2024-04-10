/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practical.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Angel
 */
@Entity
@Getter
@Setter
@ToString
@Table(name= "ToDoList")
public class ToDoList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;
   @Column (nullable = false, unique = true)
   private String title;
   @Column (nullable = false)
   private boolean done;
}
