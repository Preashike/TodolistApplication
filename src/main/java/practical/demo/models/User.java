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
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Angel
 */
    @Entity
    @Getter
    @ToString
    @Setter
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;
   
   private String firstname;
   
   private String lastname;
   
   @Column
   private String otherNames;
   
   @Column(nullable = false, unique = true)
   private String username;
   
   @Column(nullable = false)
   private String password;
   
   @Column(nullable = false)
   private boolean deleted;

}
