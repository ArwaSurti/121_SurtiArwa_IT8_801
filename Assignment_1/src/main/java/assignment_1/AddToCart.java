/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package assignment_1;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author krishnaiya
 */
@WebServlet(name = "AddToCart", urlPatterns = {"/AddToCart"})
public class AddToCart extends HttpServlet {

   
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddToCart</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddToCart at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
//        processRequest(request, response);

    int productId=Integer.parseInt(request.getParameter("product_id"));
    int qty=Integer.parseInt(request.getParameter("quantity"));
    
    HttpSession session=request.getSession();
    
    Map<Integer,CartItem> cart=(Map<Integer,CartItem>) session.getAttribute("cart");
    if(cart==null){
        cart=new HashMap<>();
    }
        try{
            
            Connection con=DbConnection.getConnection();
            String sql="SELECT * FROM product_master where product_id=?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, productId);
            ResultSet rs=ps.executeQuery();
            
            if(rs.next()){
                ProductMaster product=new ProductMaster();
                product.setProduct_id(rs.getInt("product_id"));
                product.setPrice(rs.getDouble("price"));
                product.setProduct_name(rs.getString("product_name"));
                product.setStock(rs.getInt("stock"));
                
                if(cart.containsKey(productId)){
                    CartItem item=cart.get(productId);
                    item.setQuantity(item.getQuantity()+qty);
                }else{
                    cart.put(productId,new CartItem(product,qty));
                }
            }
            
            session.setAttribute("cart", cart);
            response.sendRedirect("ViewCart.jsp");
            
        }catch(Exception e){
            
        }
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
