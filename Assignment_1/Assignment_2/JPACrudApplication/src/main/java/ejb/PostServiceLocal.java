/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/SessionLocal.java to edit this template
 */
package ejb;

import Entity.Post;
import jakarta.ejb.Local;
import java.util.List;

/**
 *
 * @author krishnaiya
 */
@Local
public interface PostServiceLocal {
     void createPost(String title,String content,int user_id,List<Integer> categoryIds);
     void updatePost(int pid,String title,String content,int user_id,List<Integer> categoryIds);
     void removePost(int pid);
     void removeCategoryFromPost(int pid,int cid);
     Post getPostById(int pid);
     List<Post> getAllPostByUserId(int uid);
     List<Post> getAllPostByCategoryId(int cid);
     
}
