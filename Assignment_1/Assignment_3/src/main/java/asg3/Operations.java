/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asg3;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author krishnaiya
 */
public class Operations {
    Connection connection;
    
    public Operations(){
        connection=DbConnection.getConnection();
    }
    
    public void addCategory(String cname){
        try {
            PreparedStatement ps=connection.prepareCall("INSERT INTO category(category_name) VALUES(?)");
            ps.setString(1, cname);
            
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateCategory(int cid,String cname){
        try {
            PreparedStatement ps=connection.prepareCall("update category set category_name=? where category_id=?");
            ps.setString(1, cname);
            ps.setInt(2, cid);
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void removeCategory(int cid){
        try {
            PreparedStatement ps=connection.prepareCall("delete from category where category_id=?");
            ps.setInt(1, cid);
            
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public CategoryMaster getCategoryById(int cid){
        ResultSet rs = null;
        CategoryMaster c=null;
        try {
            PreparedStatement ps=connection.prepareCall("Select * from category where category_id=?");
            ps.setInt(1, cid);
            
            rs=ps.executeQuery();
            
            while(rs.next()){
               c=new CategoryMaster(rs.getInt("category_id"),rs.getString("category_name"));  
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
        }
         return c;    
    }
    public List<CategoryMaster> getCategory(){
        ResultSet rs = null;
        List<CategoryMaster> data=new ArrayList<>();
        
       
        try {
            PreparedStatement ps=connection.prepareCall("Select * from category");
            rs=ps.executeQuery();

            while(rs.next()){
                data.add(new CategoryMaster(rs.getInt("category_id"),rs.getString("category_name")));
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }  
}
