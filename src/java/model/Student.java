/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author OBENNY
 */
public class Student extends Person
{
    private String studentId;
    private String dept;
    private String advisor;

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
    
}
