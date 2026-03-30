/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ejb;

import Entity.Post;
import Entity.Profile;
import jakarta.ejb.EJB;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author krishnaiya
 */
@WebServlet(name = "Demo", urlPatterns = {"/Demo"})
public class Demo extends HttpServlet {

    @EJB
    UserServiceLocal userService;
    
    @EJB
    ProfileServiceLocal profileService;
    
    @EJB
    PostServiceLocal postService;
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Demo</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Demo at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        userService.createUser("Krishnaiya", "vesuwalak@gmail.com");
//        System.out.println(userService.getUserById(3));
//        userService.updateUser(3, "Krishnaiya", "abc@gmail.com");
//        System.out.println(userService.getUserById(3));
//        userService.removeUser(2);
//       System.out.println(userService.getUserById(3));

//         profileService.createProfile("1234567890", "ABC", 5);
//         profileService.updateProfile(3, "123456789", "hk", 5);
//         System.out.println(profileService.getProfileById(3));

//        postService.createPost("Node.js", "Node is nice language", 3,new ArrayList<>(List.of(3,2,1)));
//        postService.createPost("java", "java is nice language", 3,new ArrayList<>(List.of(1,2,3)));
//  
//        postService.removeCategoryFromPost(1, 2);
//        postService.removePost(1);
//        List<Post> posts=postService.getAllPostByCategoryId(1);
//        for(Post p:posts){
//            System.out.println(p);
//        }
        System.out.print(userService.getProfileByUserId(3).getAddress());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
