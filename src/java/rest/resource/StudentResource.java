/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.resource;

import dao.StudentHandler;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import rest.model.StudentDAO;


/**
 *
 * @author OBENNY
 */
@Path("/student")
public class StudentResource {

//    private StudentDAO student = new StudentDAO("fname", "lname", "dept", "address", "phone", "stdid", "advisor", "email", "uname", "1997-01-01");
    
    private StudentDAO student = new StudentDAO();
    
    @Context
    UriInfo uriInfo;

    @Context
    Request request;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String respondAsReady() {
        return "Demo service is ready!";
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public StudentDAO getStudent(@PathParam("id") String id) {
        
        return fetchStudent(id);
    }
    
    @GET
    @Path("sample")
    @Produces(MediaType.APPLICATION_JSON)
    public StudentDAO getSampleStudent() {
        System.out.println("Returning sample student: " + student.getFirstName() + " " + student.getLastName());
        return student;
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public StudentDAO postStudent(MultivaluedMap<String, String> studentParams) {
        System.out.println("in post sevice");
        return student;
    }
    
    public StudentDAO fetchStudent(String id)
    {
        student = StudentHandler.getStudent(id);
        System.out.println("Student: " + student.getFirstName() + ", with dob " + student.getDob() + " data fetched for service");
        return student;
    }

}
