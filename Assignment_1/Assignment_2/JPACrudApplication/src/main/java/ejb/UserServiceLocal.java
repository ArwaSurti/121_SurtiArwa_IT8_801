/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/SessionLocal.java to edit this template
 */
package ejb;

import Entity.Post;
import Entity.Profile;
import Entity.Users;
import jakarta.ejb.Local;
import java.util.*;

/**
 *
 * @author krishnaiya
 */
@Local
public interface UserServiceLocal {
    void createUser(String name,String email);
    void updateUser(int user_id,String name,String email);
    void removeUser(int user_id);
    List<Post> getAllPostByUserId(int user_id);
    Users getUserById(int user_id);
    Profile getProfileByUserId(int uid);
}
