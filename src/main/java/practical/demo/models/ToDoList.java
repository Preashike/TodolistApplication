/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practical.demo.models;

import jakarta.persistence.*;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
   @CreationTimestamp
   @Column (name = "created_at", nullable = false, updatable = false)
   private Date createdat;
   @UpdateTimestamp
   @Column (name = "updated_at")
   private Date updatedat;

   @ManyToOne()
   @JoinColumn(name = "user_id")
   private User user;
}
