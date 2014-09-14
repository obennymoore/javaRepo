/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import data.UserDB;
import model.Administrator;
import model.Instructor;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author OBENNY
 */
public class DeleteServlet extends HttpServlet {

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
            out.println("<title>Servlet deleteServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet deleteServlet at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
        
        String id = request.getParameter("id");
        String type = request.getParameter("type");
        
        int rows = 0;
        String students = "";
        String instructors = "";
        String courses = "";
        String message = "";
        String url = "";
        String instructorClassList = "";
        
        Administrator administrator = null;
        Instructor instructor = null;
      
        int category = !type.equals("CL_ID") ? 1 : 2;
      
        HttpSession session= request.getSession();
        
        switch(category)
        {
            case 1:
                administrator = (Administrator) session.getAttribute("admin");
                break;
            case 2:
                instructor = (Instructor) session.getAttribute("instr");
                break;
            default:
                System.out.println("***SHOULD NEVER COME IN HERE SINCE ONLY ADMINISTRATOR AND INSTRUCTOR CAN DELETE ANY INFORMATION***");
                break;
        }
        
        switch(category)
        {
            case 1:
                System.out.println("Administrator " + type + " delete!!!");
                switch(type)
                {
                    case "I_ID":
                        rows = UserDB.removeInstructor(id);
                        break;
                    case "S_ID":
                        rows = UserDB.removeStudent(id);
                        break;
                    case "C_ID":
                        rows = UserDB.removeCourse(id);
                        break;
                    default:
                        break;
                }
                
                instructors = UserDB.getInstructors();
                students = UserDB.getStudents();
                courses = UserDB.getCourses();
                if(rows > 0)
                {
                    message = "Delete successful..." + rows + " row(s) affected!!!";
                    url = "/admin.jsp";
                }
                break;
                
            case 2:
                System.out.println("Instructor Delete!!!");
                rows = UserDB.removeClass(id);
                instructorClassList = UserDB.getInstructorClasses(instructor.getInstructorId());
                if(rows > 0)
                {
                    message = "Delete successful..." + rows + " row(s) affected!!!";
                    url = "/instructor.jsp";
                }
                break;
                
            default:
                break;
        }
        
        switch(category)
        {
            case 1:
                session.setAttribute("admin", administrator);
                session.setAttribute("courses", courses);
                session.setAttribute("instructors", instructors);
                session.setAttribute("students", students); 
                request.setAttribute("message", message);
                break;
            case 2:
                session.setAttribute("instr", instructor);
                session.setAttribute("instrClasses", instructorClassList);
                request.setAttribute("message", message);
                break;
        }
        
        
       
        
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
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
        processRequest(request, response);
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
