/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rest.model;

import java.sql.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author OBENNY
 */
@XmlRootElement
public class StudentDAO {
    
    public StudentDAO()
    {
//        category = 3;
//        status = 1;
//        firstName = "";
//        lastName = "";
//        address = "";
//        phone = "";
//        email = "";
//        uName = "";
//        studentId = "";
//        dept = "";
//        advisor = "";
//        dob = null;
        
    }
    
     
    public StudentDAO(String fName, String lName, String dept, String address, String phone,String studentId, String advisor, String email, String uName, String dob)
    {
        this.category = 3;
        this.status = 1;
        this.firstName = fName;
        this.lastName = lName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.uName = uName;
        this.studentId = studentId;
        this.dept = dept;
        this.advisor = advisor;
        this.dob = dob;
        
        
    }
    
    private int category;
    private int status;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String email;
    private String uName;
    private String studentId;
    private String dept;
    private String advisor;
    private String dob;

    
    /**
     * @return the category
     */
    public int getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(int category) {
        this.category = category;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the uName
     */
    public String getuName() {
        return uName;
    }

    /**
     * @param uName the uName to set
     */
    public void setuName(String uName) {
        this.uName = uName;
    }

    /**
     * @return the studentId
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * @param studentId the studentId to set
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * @return the dept
     */
    public String getDept() {
        return dept;
    }

    /**
     * @param dept the dept to set
     */
    public void setDept(String dept) {
        this.dept = dept;
    }

    /**
     * @return the advisor
     */
    public String getAdvisor() {
        return advisor;
    }

    /**
     * @param advisor the advisor to set
     */
    public void setAdvisor(String advisor) {
        this.advisor = advisor;
    }

    /**
     * @return the dob
     */
    public String getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(String dob) {
        this.dob = dob;
    }
    
}
