/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package ejb;

import Entity.Category;
import Entity.Post;
import Entity.Users;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author krishnaiya
 */
@Stateless
public class PostService implements PostServiceLocal {

    @PersistenceContext(unitName="content")
    EntityManager em;
    
    @Override
    public void createPost(String title, String content, int user_id,List<Integer> categoryIds) {
        Post post=new Post();
        post.setTitle(title);
        post.setContent(content);      
        
        Users user=em.find(Users.class,user_id);
        post.setUserId(user);
        
        List<Category> categories=new ArrayList<>();
        for(Integer cid:categoryIds){
            Category c=em.find(Category.class,cid);
            categories.add(c);
            
            c.getPostCollection().add(post);
        }
        
        post.setCategoryCollection(categories);
        user.getPostCollection().add(post);
        
        em.persist(post);
        
    }

    @Override
    public void updatePost(int pid, String title, String content, int user_id,List<Integer> categoryIds) {
        Post post = em.find(Post.class,pid);
        post.setTitle(title);
        post.setContent(content);
        
        Users user= em.find(Users.class, user_id);
        post.setUserId(user);
        
        List<Category> categories=new ArrayList<>();
        for(Integer cid:categoryIds){
            Category c=em.find(Category.class,cid);
            categories.add(c);
            c.getPostCollection().add(post);
        }
        post.setCategoryCollection(categories);
        em.merge(post);       
    }

    @Override
    public void removePost(int pid) {
        Post post=em.find(Post.class,pid);
        post.getCategoryCollection().clear();
        em.remove(post);
    }

    @Override
    public Post getPostById(int pid) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Post> getAllPostByUserId(int uid) {
        return em.createQuery("SELECT p FROM Post p WHERE p.userId.userId=:uid",Post.class)
                .setParameter("uid", uid).getResultList();
    }

    @Override
    public void removeCategoryFromPost(int pid, int cid) {
        Post post=em.find(Post.class,pid);
        Category category=em.find(Category.class, cid);
        post.getCategoryCollection().remove(category);
        category.getPostCollection().remove(post);
    }

    @Override
    public List<Post> getAllPostByCategoryId(int cid) {
        return em.createQuery("SELECT p FROM Post p JOIN p.categoryCollection c WHERE c.categoryId=:cid",Post.class)
                .setParameter("cid", cid).getResultList();
    }
    

   
}
