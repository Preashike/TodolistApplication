/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practical.demo.controllers;


import practical.demo.dtos.request.UsernameRequest;
import practical.demo.dtos.request.UserUpdateRequest1;
import practical.demo.models.User;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import practical.demo.repositories.UserRepository;

/**
 *
 * @author Angel
 */
@RestController
@RequestMapping("/users")
public class UserController {

@Autowired   
private UserRepository userRepository;
        
@GetMapping("/")
public List<User> getUsers(){
    return userRepository.findAllByDeleted(false);
}

@PostMapping("/register")
public String register(@RequestBody UsernameRequest data){
    System.out.println(data);
    Optional<User> optionalUser = userRepository.findByUsernameAndDeleted(data.getUsername(), false);
    if(optionalUser.isPresent()){
        return "User Already Exist";
    }
    User user = new User();
    user.setPassword(data.getPassword());
    user.setUsername(data.getUsername());
    userRepository.save(user);
    return "Successful";
}

@GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        Optional<User> optionalUser = userRepository.findByIdAndDeleted(id,false);
        if(optionalUser.isEmpty()){
            return null;
        }
        User user = optionalUser.get();
        return user;
}

    @PutMapping("/update")
    public Object updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest1 request){
        Optional<User> optionalUser = userRepository.findByIdAndDeleted(id,false);
        if(optionalUser.isEmpty()){
            return "Invalid User";
        }

        Optional<User> byUsername = userRepository.findByUsernameAndDeleted(request.getUsername(),false);
        if(byUsername.isPresent()){
            User user = byUsername.get();
            if(user.getId()!=id) {
                return "Username Already Exist";
            }
        }

        User user = optionalUser.get();
        user.setUsername(request.getUsername());
        user.setFirstname(request.getFirstname());
        user.setOtherNames(request.getOthername());
        user.setLastname(request.getLastname());

        userRepository.save(user);
        return "Successful";
    }

    @DeleteMapping("{id}/hard-delete")
    public Object hardDeleteUser(@PathVariable Long id){
        boolean existsById = userRepository.existsByIdAndDeleted(id,false);
        if(!existsById){
            return "Invalid User";
        }
        userRepository.deleteById(id);
        return "Successful";
    }

    @DeleteMapping("/{id}/soft-delete")
    public Object deleteUser(@PathVariable Long id){
        Optional<User> optionalUser = userRepository.findByIdAndDeleted(id,false);
        if(optionalUser.isEmpty()){
            return "Invalid User";
        }
        User user = optionalUser.get();
        user.setDeleted(true);
        userRepository.save(user);

        return "Successful";
    }

}
