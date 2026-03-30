/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package ejb;

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
public class ProfileService implements ProfileServiceLocal {

    @PersistenceContext(unitName="content")
    EntityManager em;
    @Override
    public void createProfile(String phone, String address, int uid) {
        Profile profile=new Profile();
        Users user=em.find(Users.class, uid);
        profile.setUserId(user);
        profile.setPhone(phone);
        profile.setAddress(address);
        
        em.persist(profile);
    }

    @Override
    public void updateProfile(int pid, String phone, String address, int uid) {
        Profile profile=getProfileById(pid);
        if(profile!=null){
//            Users user=em.find(Users.class, uid);
//            profile.setUserId(user);
            profile.setAddress(address);
            profile.setPhone(phone);
            
            em.merge(profile);
        } 
    }

    @Override
    public void removeProfile(int pid) {
        Profile profile=getProfileById(pid);
        if(profile!=null){
            em.remove(profile);
        }
    }

    @Override
    public Profile getProfileById(int pid) {
        List<Profile> profile=em.createNamedQuery("Profile.findByProfileId",Profile.class).setParameter("profileId", pid).getResultList();
        if(profile == null){
            return null;
        }
        return profile.get(0);
    }

   
}
