/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package practical.demo.repositories;

import practical.demo.models.User;
import practical.demo.*;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Angel
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    List<User>findAllByDeleted(boolean deleted);
    List<User>findByFirstname(String firstname);
    Optional<User>findByUsernameAndDeleted(String username,boolean deleted);
    Optional<User>findByUsername(String username);
    Optional<User>findByIdAndDeleted(Long id,boolean deleted);
    boolean existsByIdAndDeleted(Long id, boolean deleted);
}
