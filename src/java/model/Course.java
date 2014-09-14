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
public class Course 
{
    private String courseId;
    private String courseName;
    private String courseDesc;
    private int courseDept;
    private int units;

    /**
     * @return the courseId
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     * @param courseId the courseId to set
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    /**
     * @return the courseName
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * @param courseName the courseName to set
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * @return the courseDesc
     */
    public String getCourseDesc() {
        return courseDesc;
    }

    /**
     * @param courseDesc the courseDesc to set
     */
    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    /**
     * @return the courseDept
     */
    public int getCourseDept() {
        return courseDept;
    }

    /**
     * @param courseDept the courseDept to set
     */
    public void setCourseDept(int courseDept) {
        this.courseDept = courseDept;
    }

    /**
     * @return the units
     */
    public int getUnits() {
        return units;
    }

    /**
     * @param units the units to set
     */
    public void setUnits(int units) {
        this.units = units;
    }
}
