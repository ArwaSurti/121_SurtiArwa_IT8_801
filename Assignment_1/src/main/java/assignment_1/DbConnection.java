/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author krishnaiya
 */
public class DbConnection {
    public static Connection getConnection(){
       Connection con=null;
       
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment_1?useSSL=false","root","root");
            System.out.println("Database connected successfully");
        } catch (SQLException ex) {
            //Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
       return con;
    }
}
