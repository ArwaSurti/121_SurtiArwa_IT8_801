/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package asg3;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
@WebServlet(name = "Category", urlPatterns = {"/Category"})
public class Category extends HttpServlet {

   Operations op=new Operations();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Category</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Category at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action=request.getParameter("action");
        String id=request.getParameter("id");
        CategoryMaster c=null;
        
        if(action!=null){
            if("delete".equals(action)){
                op.removeCategory(Integer.parseInt(id));
            }
            else if("edit".equals(action)){
                c=op.getCategoryById(Integer.parseInt(id));
            }
        }
        
        List<CategoryMaster> data=op.getCategory();
        request.setAttribute("e",c);
        request.setAttribute("data", data);
        request.getRequestDispatcher("Category.jsp").forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String cname=request.getParameter("cname");
        String id=request.getParameter("id");
        
        if(id == null || id.isEmpty()){
            op.addCategory(cname);
        }else{
            op.updateCategory(Integer.parseInt(id), cname);
        }
    
        List<CategoryMaster> data=op.getCategory();
        request.setAttribute("data", data);
        request.getRequestDispatcher("Category.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
