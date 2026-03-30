/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/SessionLocal.java to edit this template
 */
package ejb;

import Entity.Profile;
import jakarta.ejb.Local;
import java.util.List;

/**
 *
 * @author krishnaiya
 */
@Local
public interface ProfileServiceLocal {
    void createProfile(String phone,String address,int uid);
    void updateProfile(int pid,String phone,String address,int uid);
    void removeProfile(int pid);
    Profile getProfileById(int pid);
}
