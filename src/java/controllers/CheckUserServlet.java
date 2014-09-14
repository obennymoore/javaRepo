/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import data.ConnectionPool;
import data.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;
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
public class CheckUserServlet extends HttpServlet {

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
            out.println("<title>Servlet CheckUserServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckUserServlet at " + request.getContextPath() + "</h1>");
            out.println("<p>Your user details: " + request.getParameter("userid") + " " + request.getParameter("password") + "</p>");
            if(UserDB.userExists(request.getParameter("userid")))
                out.println("<p><b>User Exists!!!</b></p>");
            else
                out.println("<p><b>User Invalid!!!</b></p>");
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
        Person user = null;
        Administrator administrator = null;
        Student student = null;
        Instructor instructor = null;
        
        List<Department> departments = null;
        List<String> advisorList = null;
        List<String> studentList = null;
        List<Course> courseList = null;
        
        
        int category = 0;
        
        String uname = request.getParameter("userid");
        String pwd = request.getParameter("password");
        
        String courses = "";
        String instructors = "";
        String instructorClassList = "";
        String students = "";
        String url = "";
        String message = "";
        
        if(UserDB.userExists(uname))
        {
           user = UserDB.getPerson(uname, 1);
           if(!user.getPwd().equals(pwd))
           {
               message = "Incorrect password!!!";
               url = "/login.jsp";
           }
            else
           {
               category = user.getCategory();
               switch(category)
               {
                   case 1:
                       administrator = UserDB.getAdministrator(uname);
                       courses = UserDB.getCourses();
                       instructors = UserDB.getInstructors();
                       students = UserDB.getStudents();
                       departments = UserDB.getDepartments();
                       advisorList = UserDB.getAdvisorList();
                       url = "/admin.jsp";
                       break;
                   case 2:
                       instructor = UserDB.getInstructor(uname);
                       System.out.println("\nInstructor " + instructor.getFirstName() + "retrieved!!!");
                       instructorClassList = UserDB.getInstructorClasses(instructor.getInstructorId());
                       courseList = UserDB.getCourseList();
                       studentList = UserDB.getStudentList();
                       url = "/instructor.jsp";
                       break;
                   case 3:
                       student = UserDB.getStudent(uname);
                       courses = UserDB.getStudentCourses(student.getStudentId());
                       url = "/student.jsp";
                       break;
                   default:
                       break;
               }
           }
           
        }
        else
        {
            message = "User does not exist!!!";
            url = "/login.jsp";
        }
        
        HttpSession session = request.getSession();
        
        switch(category)
        {
            case 1:
                session.setAttribute("admin", administrator);
                session.setAttribute("courses", courses);
                session.setAttribute("instructors", instructors);
                session.setAttribute("students", students);
                session.setAttribute("departments", departments);
                session.setAttribute("advisors", advisorList);
                break;
            case 2:
                session.setAttribute("instr", instructor);
                session.setAttribute("instrClasses", instructorClassList);
                session.setAttribute("courseList", courseList);
                session.setAttribute("studentList", studentList);
                break;
            case 3:
                session.setAttribute("student", student);
                session.setAttribute("courses", courses);
                break;
            default:
                request.setAttribute("message", message);
                break;
        }
                
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
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
