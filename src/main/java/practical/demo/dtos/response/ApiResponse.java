/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practical.demo.dtos.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Angel
 */
@Getter
@Setter
@ToString
public class ApiResponse {
   private int status;
   private String message;
   private Object data;
}
