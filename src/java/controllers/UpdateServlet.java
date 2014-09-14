/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import data.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.*;

/**
 *
 * @author OBENNY
 */
public class UpdateServlet extends HttpServlet {

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
            out.println("<title>Servlet updateStudentServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet updateStudentServlet at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
        
        int rows = 0;
        String courses = "";
        String instructors = "";
        String students = "";
        String message = "";
        String url = "";
        
        String category = request.getParameter("category");
        
        Student student = null;
        Instructor instructor = null;
        Administrator administrator = null;
        
        HttpSession session = request.getSession();
        
        System.out.println("Update Category: " + category);
        
        switch(category)
        {
            case "1":
                administrator = (Administrator) session.getAttribute("admin");
                
                break;
            case "2":
                instructor = (Instructor) session.getAttribute("instr");
                break;
            case "3":
                student = (Student) session.getAttribute("student");
                break;
        }
        
        String fName = request.getParameter("firstName");
        String lName = request.getParameter("lastName");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String dob = request.getParameter("dob");
    
        switch(category)
        {
            case "1":
                int adminId = administrator.getId();
                
                System.out.println("Category " + category + " Administrator " + adminId);
                
                rows = UserDB.updateAdministrator(adminId, fName, lName, address, phone, email, dob);
                
                if(rows > 0)
                {
                    administrator = UserDB.getAdministrator(administrator.getuName());
                    message = "update successful..." + rows + " row(s) affected!!!";
                    url = "/admin.jsp";
                }
                break;
            case "2":
                String instructorId = instructor.getInstructorId();
                
                System.out.println("Category " + category + " Instructor " + instructorId);
                
                rows = UserDB.updateInstructor(instructorId , fName, lName, address, phone, email);
                
                if(rows > 0)
                {
                    instructor = UserDB.getInstructor(instructor.getuName());
                    message = "update successful..." + rows + " row(s) affected!!!";
                    url = "/instructor.jsp";
                }
                
                break;
            case "3": 
                String studentId = student.getStudentId();
                
                
                System.out.println("Category " + category + " Student " + studentId);
                
                rows = UserDB.updateStudent(studentId, fName, lName, address, phone, email);
                
                if(rows > 0)
                {
                    student = UserDB.getStudent(student.getuName());
                    message = "update successful..." + rows + " row(s) affected!!!";
                    url = "/student.jsp";
                }
                break;
            default:
                break;
        }
        
        switch(category)
        {
            case "1":
                session.setAttribute("admin", administrator);
                request.setAttribute("message", message);
                break;
            case "2":
                session.setAttribute("instr", instructor);
                request.setAttribute("message", message);
                break;
            case "3":
                session.setAttribute("student", student);
                request.setAttribute("message", message);
                break;
            default:
                break;
        }
                
        RequestDispatcher dispatcher;
        dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
         
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
