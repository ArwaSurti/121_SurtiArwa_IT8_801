/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package ejb;

import Entity.Post;
import Entity.Profile;
import Entity.Users;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author krishnaiya
 */
@Stateless
public class UserService implements UserServiceLocal {
    
    @PersistenceContext(unitName="content")
    private EntityManager em;
    

    @Override
    public void createUser(String name, String email) {

        Users user=new Users();
        user.setName(name);
        user.setEmail(email);
        
        em.persist(user);
    }

    @Override
    public void updateUser(int user_id, String name, String email) {
        Users user=getUserById(user_id);
        if(user!=null){
            user.setName(name);
            user.setEmail(email);
        }
        em.merge(user);
    }

    @Override
    public void removeUser(int user_id) {
        Users user=getUserById(user_id);
        if(user!=null){
            em.remove(user);
        }
    }

    @Override
    public List<Post> getAllPostByUserId(int user_id) {
        List<Post> posts=em.createQuery("SELECT p FROM Post WHERE p.userId.userId=:userId")
                .setParameter("userId", user_id)
                .getResultList();
        
        if(posts==null){
            return null;
        }
        return posts;
    }

    @Override
    public Users getUserById(int user_id) {
        
        List<Users> user=em.createNamedQuery("Users.findByUserId",Users.class)
                .setParameter("userId",user_id)
                .getResultList();
        if(user == null){
            return null;
        }
        return user.get(0);
    }

    @Override
    public Profile getProfileByUserId(int uid) {
        return em.createQuery("SELECT p FROM Profile p WHERE p.userId.userId=:uid",Profile.class)
                .setParameter("uid", uid).getSingleResult();
    }

   
}
