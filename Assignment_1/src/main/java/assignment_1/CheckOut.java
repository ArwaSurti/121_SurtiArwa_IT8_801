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
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

/**
 *
 * @author krishnaiya
 */
@WebServlet(name = "CheckOut", urlPatterns = {"/CheckOut"})
public class CheckOut extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CheckOut</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckOut at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        Map<Integer,CartItem> cart= (Map<Integer,CartItem>) session.getAttribute("cart");
        
        if(cart == null || cart.isEmpty()){
            response.sendRedirect("ViewCart.jsp");
            return;
        }
        
        String paymentMode=request.getParameter("payment_mode");
        
        String sessionId=session.getId();
        
        double totalAmount=0;
        
        for(CartItem item:cart.values()){
            totalAmount+=item.getTotalPrice();
        }
        double tax=totalAmount*0.05;
        double finalAmount=totalAmount+tax;
        
        try{
            Connection con=DbConnection.getConnection();
            con.setAutoCommit(false);
            
            String sql="INSERT INTO order_master(order_datetime,session_id,payment_mode,tax,total_amount,order_status) VALUES(?,?,?,?,?,?)";
           
            PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(2, sessionId);
            ps.setString(3, paymentMode);
            ps.setDouble(4, tax);
            ps.setDouble(5, finalAmount);
            ps.setString(6, "PENDING");
            
            ps.executeUpdate();
            
            ResultSet rs=ps.getGeneratedKeys();
            rs.next();
            
            int orderId=rs.getInt(1);
            
            String detailSql="INSERT INTO order_details(order_id,product_id,product_price,discount) VALUES(?,?,?,?)";
            
            PreparedStatement pds=con.prepareCall(detailSql);
            
            for(CartItem item:cart.values()){
                pds.setInt(1, orderId);
                pds.setInt(2, item.getProduct().getProduct_id());
                pds.setDouble(3, item.getProduct().getPrice());
                pds.setDouble(4,0);
                pds.executeUpdate();
            }
            
            con.commit();
            
            session.removeAttribute("cart");
            session.setAttribute("orderId",orderId);
            request.getRequestDispatcher("OrderSuccess.jsp").forward(request, response);
                    
            
        }catch(Exception e){
            e.printStackTrace();
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
