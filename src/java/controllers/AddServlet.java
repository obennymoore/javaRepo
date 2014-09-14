/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import data.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Administrator;
import model.Instructor;
import model.Student;

/**
 *
 * @author OBENNY
 */
public class AddServlet extends HttpServlet {

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
            out.println("<title>Servlet AddServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddServlet at " + request.getContextPath() + "</h1>");
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
        String category = "";
        String instructorClassList = "";
        
        Instructor instructor = null;
        Administrator administrator = null;
        
        category = request.getParameter("category");
        
        String addCategory = request.getParameter("addcategory");
        
        HttpSession session = request.getSession();
        
        switch(category)
        {
            case "1":
                administrator = (Administrator) session.getAttribute("admin");
                break;
            case "2":
                instructor = (Instructor) session.getAttribute("instr");
                break;
            case "3":
                break;
            default:
                break;
        }
        
        
        
        
    
        switch(category)
        {
            case "1":
                String dept = request.getParameter("department");
                String uname = request.getParameter("uname");
                String fName = request.getParameter("firstName");
                String lName = request.getParameter("lastName");
                String address = request.getParameter("address");
                String phone = request.getParameter("phone");
                String email = request.getParameter("email");
                
                String dob = request.getParameter("dob");
                int adminId = administrator.getId();
                
                System.out.println("Administrator ID: " + adminId + "\nAdd Person Category: " + addCategory + "!!!");
                
                switch(addCategory)
                {
                    case "1":
                        break;
                    case "2":
                        System.out.println("****NEW INSTRUCTOR*****");
                        String instructorId = request.getParameter("instructorId");
                        double salary = Double.parseDouble(request.getParameter("salary").trim());
                        
                        rows = UserDB.addInstructor(uname, instructorId, fName, lName, dob, dept, salary, address, phone, email);
                        break;
                    case "3":
                        System.out.println("****NEW STUDENT*****");
                        String studentId = request.getParameter("studentId");
                        String advisor = request.getParameter("advisor");
                        rows = UserDB.addStudent(uname, studentId, fName, lName, dob, dept, advisor, address, phone, email);
                        break;
                    default:
                        System.out.println("****NEW COURSE*****");
                        String courseId = request.getParameter("courseId");
                        String courseName = request.getParameter("courseName");
                        String courseDesc = request.getParameter("courseDesc");
                        int credits = Integer.parseInt(request.getParameter("credits"));
                        rows = UserDB.addCourse(courseId, courseName, courseDesc, dept, credits);
                        break;                    
                }
                
                students = UserDB.getStudents();
                instructors = UserDB.getInstructors();
                courses = UserDB.getCourses();
                
                if(rows > 0)
                {
                    
                    administrator = UserDB.getAdministrator(administrator.getuName());
                    message = "insert successful..." + rows + " row(s) affected!!!";
                    url = "/admin.jsp";
                }
                break;
            case "2":
                String courseCode = request.getParameter("courseId");
                String studentCode =request.getParameter("studentId");
                
                String instructorId = instructor.getInstructorId();
                
                System.out.println("Instructor ID: " + instructorId + "\nAdd Class to reord!!!");
                
                rows = UserDB.addClass(courseCode, studentCode, instructorId);
                instructorClassList = UserDB.getInstructorClasses(instructorId);
                
                if(rows > 0)
                {
                    
                    instructor = UserDB.getInstructor(instructor.getuName());
                    message = "insert successful..." + rows + " row(s) affected!!!";
                    url = "/instructor.jsp";
                }
                break;
            case "3": 
                break;
            default:
                break;
        }
        
        switch(category)
        {
            case "1":
                session.setAttribute("admin", administrator);
                session.setAttribute("students", students);
                session.setAttribute("instructors", instructors);
                session.setAttribute("courses", courses);
                request.setAttribute("message", message);
                break;
            case "2":
                session.setAttribute("instr", instructor);
                session.setAttribute("instrClasses", instructorClassList);
                request.setAttribute("message", message);
                break;
            case "3":
//                session.setAttribute("student", student);
//                request.setAttribute("message", message);
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
