/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.*;

/**
 *
 * @author OBENNY
 */
public class UserDB {

    public static boolean userExists(String uname) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connect = pool.getConnection();
        Statement stmt = null;
        ResultSet rs = null;

        String query = "select uname from person where uname = '" + uname + "'";

        try {
            stmt = connect.createStatement();
            rs = stmt.executeQuery(query);
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closeStatement(stmt);
            pool.freeConnection(connect);
        }

    }

    public static Person getPerson(String uname, int mode) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connect = pool.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        Person person = null;

        String query = null;

        if (mode == 1) {
            query = "select * from person where lower(uname) = '" + uname + "'";
        } else {
            query = "select p_id from person where lower(uname) = '" + uname + "'";
        }

        try {
            stmt = connect.createStatement();
            rs = stmt.executeQuery(query);

            if (rs.next() && mode == 1) {
                person = new Person();
                person.setCategory(rs.getInt("cat"));
                person.setPwd(rs.getString("pwd"));
                person.setuName(rs.getString("uname"));
            } else {
                person = new Person();
                person.setId(rs.getInt("p_id"));
            }
            return person;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closeStatement(stmt);
            pool.freeConnection(connect);
        }
    }

    public static Administrator getAdministrator(String uname) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connect = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Administrator admin = null;

        String query = "select * from person where lower(uname) = ?";

        try {
            ps = connect.prepareStatement(query);
            ps.setString(1, uname);
            rs = ps.executeQuery();

            if (rs.next()) {
                admin = new Administrator();
                admin.setAddress(rs.getString("address"));
                admin.setCategory(rs.getInt("cat"));
                admin.setDob(rs.getDate("dob"));
                admin.setEmail(rs.getString("email"));
                admin.setFirstName(rs.getString("fname"));
                admin.setId(rs.getInt("p_id"));
                admin.setLastName(rs.getString("lname"));
                admin.setPhone(rs.getString("phone"));
                admin.setPwd(rs.getString("pwd"));
                admin.setStatus(rs.getInt("status"));
                admin.setuName(rs.getString("uname"));
            }
            System.err.println("Administrator " + admin.getFirstName() + " fetched");
            return admin;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connect);
        }
    }

    public static Student getStudent(String uname) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connect = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Student student = new Student();

        String advisorId = null;
        String query1 = "select s_id,fname,lname,dob,address,phone,email,cat,status,uname,pwd,d_name,i_id \n"
                + "from person p join student s on s.p_id = p.p_id join department d on d.d_id = s.d_id\n"
                + "where uname = ?";

        String query2 = "select fname || ' ' || lname advisor from person p join instructor i on i.p_id = p.p_id where i.i_id = ?";

        try {
            ps = connect.prepareStatement(query1);
            ps.setString(1, uname);
            rs = ps.executeQuery();

            if (rs.next()) {

                student.setAddress(rs.getString("address"));
                student.setCategory(rs.getInt("cat"));
                student.setDob(rs.getDate("dob"));
                student.setEmail(rs.getString("email"));
                student.setFirstName(rs.getString("fname"));
                student.setLastName(rs.getString("lname"));
                student.setPhone(rs.getString("phone"));
                student.setPwd(rs.getString("pwd"));
                student.setStatus(rs.getInt("status"));
                student.setuName(rs.getString("uname"));
                student.setStudentId(rs.getString("s_id"));
                student.setDept(rs.getString("d_name"));
                advisorId = rs.getString("i_id");
            }

            ps = connect.prepareStatement(query2);
            ps.setString(1, advisorId);
            rs = ps.executeQuery();

            if (rs.next()) {
                student.setAdvisor(rs.getString("advisor"));
            }
            System.err.println("Advisor for " + student.getFirstName() + " is " + student.getAdvisor());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connect);
        }
        return student;
    }

    public static Instructor getInstructor(String uname) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connect = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Instructor instructor = new Instructor();

        String query = "select i_id,fname,lname,dob,address,phone,email,cat,status,uname,pwd,d_name,salary \n"
                + "from person p join instructor i on i.p_id = p.p_id join department d on d.d_id = i.d_id\n"
                + "where uname = ?";

        try {
            ps = connect.prepareStatement(query);
            ps.setString(1, uname);
            rs = ps.executeQuery();

            if (rs.next()) {
                instructor.setAddress(rs.getString("address"));
                instructor.setCategory(rs.getInt("cat"));
                instructor.setDob(rs.getDate("dob"));
                instructor.setEmail(rs.getString("email"));
                instructor.setFirstName(rs.getString("fname"));
                instructor.setLastName(rs.getString("lname"));
                instructor.setPhone(rs.getString("phone"));
                instructor.setPwd(rs.getString("pwd"));
                instructor.setStatus(rs.getInt("status"));
                instructor.setuName(rs.getString("uname"));
                instructor.setDept(rs.getString("d_name"));
                instructor.setInstructorId(rs.getString("i_id"));
                instructor.setSalary(rs.getDouble("salary"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connect);
        }
        System.out.println("Instructor " + instructor.getInstructorId() + " makes " + instructor.getSalary());
        return instructor;
    }

    public static String getHtmlTable(ResultSet results, int mode) throws SQLException {
        StringBuffer htmlRows = new StringBuffer();
        ResultSetMetaData metaData = results.getMetaData();
        int columnCount = metaData.getColumnCount();

        if (mode == 0) {
            htmlRows.append("<table cellpadding=\"5\" border=\"1\">");
            htmlRows.append("<tr>");
            for (int i = 1; i <= columnCount; i++) {
                htmlRows.append("<td><b>" + metaData.getColumnName(i) + "</b></td>");
            }
            htmlRows.append("</tr>");

            while (results.next()) {
                htmlRows.append("<tr>");
                for (int i = 1; i <= columnCount; i++) {
                    String data = results.getString(i);
                    htmlRows.append("<td>" + data + "</td>");
                    System.out.println("Data on row " + i + ": " + data);
                }
            }
            htmlRows.append("</tr>");
            htmlRows.append("</table>");
        } else {
            String type = metaData.getColumnName(1);
            columnCount += 1;
            htmlRows.append("<table cellpadding=\"5\" border=\"1\">");
            htmlRows.append("<tr>");
            for (int i = 1; i <= columnCount; i++) {
                if (i != columnCount) {
                    htmlRows.append("<td><b>" + metaData.getColumnName(i) + "</b></td>");
                } else {
                    switch (type) {
                        case "I_ID":
                            htmlRows.append("<td align=\"center\"><b>ACTION<br>|<a href=\"#addInstructor\">+</a>|</b></td>");
                            break;
                        case "S_ID":
                            htmlRows.append("<td align=\"center\"><b>ACTION<br>|<a href=\"#addStudent\">+</a>|</b></td>");
                            break;
                        case "C_ID":
                            htmlRows.append("<td align=\"center\"><b>ACTION<br>|<a href=\"#addCourse\">+</a>|</b></td>");
                            break;
                        default:
                            htmlRows.append("<td align=\"center\"><b>ACTION<br>|<a href=\"#addClass\">+</a>|</b></td>");
                            break;
                    }
                }

            }
            htmlRows.append("</tr>");

            int x = 0;

            while (results.next()) {
                ++x;
                htmlRows.append("<tr>");
                for (int i = 1; i <= columnCount; i++) {
                    if (i != columnCount) {
                        htmlRows.append("<td>" + results.getString(i) + "</td>");
                    } else {
                        htmlRows.append("<td align=\"center\"><b><a href=\"delete?id=" + results.getString(1) + "&type=" + type + "\" style=\"color:#291f53\" ng-click=\"delete($event)\">X</a>|<a href=\"#edit?id=" + results.getString(1) + "\">E</a></b></td>");
                    }
                }

            }
            htmlRows.append("</form></tr>");
            htmlRows.append("</table>");
        }
        return htmlRows.toString();
    }

    public static String getStudentCourses(String id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connect = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String results;

        String query = "select c_id, c_name, grade from class join course using(c_id) where s_id = ?";

        try {
            ps = connect.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();

            results = getHtmlTable(rs, 0);

            return results;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connect);
        }
    }

    public static String getInstructorClasses(String id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connect = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String results;

        String query = "select cl.cl_id, cs.c_name from class cl join course cs on (cs.C_ID = cl.C_ID) and cl.i_id = ?";

        try {
            ps = connect.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();

            results = getHtmlTable(rs, 1);

            return results;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connect);
        }
    }

    public static String getCourses() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connect = pool.getConnection();
        Statement stmt = null;
        ResultSet rs = null;

        String results;

        String query = "select * from course";

        try {
            stmt = connect.createStatement();
            rs = stmt.executeQuery(query);

            results = getHtmlTable(rs, 1);

            return results;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closeStatement(stmt);
            pool.freeConnection(connect);
        }
    }

    public static List<Department> getDepartments() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connect = pool.getConnection();
        Statement stmt = null;
        ResultSet rs = null;

        List<Department> depts = new ArrayList<Department>();

        String query = "select d_id,d_code,d_name from department";

        try {
            stmt = connect.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Department dept = new Department();
                dept.setDeptId(rs.getInt("d_id"));
                dept.setDeptCode(rs.getString("d_code"));
                dept.setDeptName(rs.getString("d_name"));
                depts.add(dept);
            }
            return depts;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closeStatement(stmt);
            pool.freeConnection(connect);
        }
    }

    public static List<String> getStudentList() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connect = pool.getConnection();
        Statement stmt = null;
        ResultSet rs = null;

        List<String> students = new ArrayList<String>();

        String query = "select s_id from student";

        try {
            stmt = connect.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                String studentId = rs.getString("s_id");
                students.add(studentId);
            }
            return students;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closeStatement(stmt);
            pool.freeConnection(connect);
        }
    }

    public static List<String> getAdvisorList() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connect = pool.getConnection();
        Statement stmt = null;
        ResultSet rs = null;

        List<String> advisors = new ArrayList<String>();

        String query = "select i_id from instructor";

        try {
            stmt = connect.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                String instructorId = rs.getString("i_id");
                advisors.add(instructorId);
            }
            return advisors;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closeStatement(stmt);
            pool.freeConnection(connect);
        }
    }

    public static List<Course> getCourseList() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connect = pool.getConnection();
        Statement stmt = null;
        ResultSet rs = null;

        List<Course> courses = new ArrayList<Course>();

        String query = "select * from course";

        try {
            stmt = connect.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Course course = new Course();
                course.setCourseId(rs.getString("c_id"));
                course.setCourseName(rs.getString("c_name"));
                course.setCourseDesc(rs.getString("c_desc"));
                course.setCourseDept(rs.getInt("d_id"));
                course.setUnits(rs.getInt("units"));

                courses.add(course);
            }
            return courses;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closeStatement(stmt);
            pool.freeConnection(connect);
        }
    }

    public static String getInstructors() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connect = pool.getConnection();
        Statement stmt = null;
        ResultSet rs = null;

        String results;

        String query = "select i_id,fname,lname,d_name from person p join instructor i on i.p_id = p.p_id join department d on d.d_id = i.d_id";

        try {
            stmt = connect.createStatement();
            rs = stmt.executeQuery(query);

            results = getHtmlTable(rs, 1);

            return results;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closeStatement(stmt);
            pool.freeConnection(connect);
        }
    }

    public static String getStudents() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connect = pool.getConnection();
        Statement stmt = null;
        ResultSet rs = null;

        String results;

        String query = "select s_id,fname,lname,d_name from person p join student s on s.p_id = p.p_id join department d on d.d_id = s.d_id";

        try {
            stmt = connect.createStatement();
            rs = stmt.executeQuery(query);

            results = getHtmlTable(rs, 1);

            return results;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closeStatement(stmt);
            pool.freeConnection(connect);
        }
    }

    public static int removeCourse(String id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connect = pool.getConnection();
        Statement stmt = null;

        int rowCount = 0;

        String query = "delete from course where c_id = '" + id + "'";

        try {
            stmt = connect.createStatement();
            rowCount = stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement(stmt);
            pool.freeConnection(connect);
        }
        if (rowCount > 0) {
            System.out.println("Course " + id + " deleted");
        }
        return rowCount;
    }

    public static int updateStudent(String id, String fName, String lName, String address, String phone, String email) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connect = pool.getConnection();
        PreparedStatement ps = null;

        int rowCount = 0;

        String query = "update person set "
                + " fname = upper(?),"
                + " lname =upper(?),"
                + " address = upper(?),"
                + " phone = upper(?),"
                + " email = upper(?)"
                + " where p_id = (select p_id from student where s_id = ?)";

        try {
            ps = connect.prepareStatement(query);
            ps.setString(1, fName);
            ps.setString(2, lName);
            ps.setString(3, address);
            ps.setString(4, phone);
            ps.setString(5, email);
            ps.setString(6, id);
            rowCount = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connect);
        }
        if (rowCount > 0) {
            System.out.println("Student " + id + "updated!!!");
        }
        return rowCount;
    }

    public static int updateInstructor(String id, String fName, String lName, String address, String phone, String email) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connect = pool.getConnection();
        PreparedStatement ps = null;

        int rowCount = 0;

        String query = "update person set "
                + " fname = upper(?),"
                + " lname =upper(?),"
                + " address = upper(?),"
                + " phone = upper(?),"
                + " email = upper(?)"
                + " where p_id = (select p_id from instructor where i_id = ?)";

        try {
            ps = connect.prepareStatement(query);
            ps.setString(1, fName);
            ps.setString(2, lName);
            ps.setString(3, address);
            ps.setString(4, phone);
            ps.setString(5, email);
            ps.setString(6, id);
            rowCount = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connect);
        }
        if (rowCount > 0) {
            System.out.println("Instructor " + id + "updated!!!");
        }
        return rowCount;
    }

    public static int updateAdministrator(int id, String fName, String lName, String address, String phone, String email, String dob) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connect = pool.getConnection();
        PreparedStatement ps = null;

        Date d = Date.valueOf(dob);

        int rowCount = 0;

        String query = "update person set "
                + " fname = upper(?),"
                + " lname =upper(?),"
                + " address = upper(?),"
                + " phone = upper(?),"
                + " email = upper(?),"
                + " dob = ?"
                + " where p_id = ?";

        try {
            ps = connect.prepareStatement(query);
            ps.setString(1, fName);
            ps.setString(2, lName);
            ps.setString(3, address);
            ps.setString(4, phone);
            ps.setString(5, email);
            ps.setDate(6, d);
            ps.setInt(7, id);
            rowCount = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connect);
        }
        if (rowCount > 0) {
            System.out.println("Administrator " + fName + " updated!!!");
        }
        return rowCount;
    }

    public static int addStudent(String uname, String studentId, String fName, String lName, String dob, String dept, String advisor, String address, String phone, String email) {
        System.out.print("Student Information--->"
                + "\nUN: " + uname
                + "\nID: " + studentId
                + "\nFN: " + fName
                + "\nLN: " + lName
                + "\nDOB: " + dob
                + "\nDP: " + dept
                + "\nAD: " + advisor
                + "\nADD: " + address
                + "\nPH: " + phone
                + "\nEM: " + email + "\n");

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connect = pool.getConnection();
        PreparedStatement ps = null;

        //String primaryKey = "person_seq.nextval()";
        Date d = Date.valueOf(dob);

        int rowCount = 0;

        String query1 = "insert into person (fname,lname,pwd,dob,address,phone,email,cat,status,uname) values("
                + " upper(?),"
                + " upper(?),"
                + " ?,"
                + " ?,"
                + " upper(?),"
                + " upper(?),"
                + " upper(?),"
                + " ?,"
                + " ?,"
                + " ?) ";

        String query2 = "insert into student values("
                + " upper(?),"
                + " ?,"
                + " upper(?),"
                + " ?)";

        try {
            ps = connect.prepareStatement(query1);
            //ps.setString(1, primaryKey);
            ps.setString(1, fName);
            ps.setString(2, lName);
            ps.setString(3, "newpassword");
            ps.setDate(4, d);
            ps.setString(5, address);
            ps.setString(6, phone);
            ps.setString(7, email);
            ps.setInt(8, 3);
            ps.setInt(9, 0);
            ps.setString(10, uname);

            rowCount = ps.executeUpdate();

            System.out.println("*****UP TO THIS POINT*****");

            if (rowCount > 0) {
                rowCount = 0;
                System.out.println("Person " + uname + "added successfully!!!");
                Person user = getPerson(uname, 0);
                ps = connect.prepareStatement(query2);
                ps.setString(1, studentId);
                ps.setInt(2, user.getId());
                ps.setString(3, advisor);
                ps.setInt(4, Integer.parseInt(dept));
                rowCount = ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connect);
        }
        if (rowCount > 0) {
            System.out.println("Student " + studentId + "added!!!");
        }
        return rowCount;
    }

    public static int addInstructor(String uname, String instructorId, String fName, String lName, String dob, String dept, double salary, String address, String phone, String email) {
        System.out.print("Instructor Information--->"
                + "\nUN: " + uname
                + "\nID: " + instructorId
                + "\nFN: " + fName
                + "\nLN: " + lName
                + "\nDOB: " + dob
                + "\nDP: " + dept
                + "\nAD: " + salary
                + "\nADD: " + address
                + "\nPH: " + phone
                + "\nEM: " + email + "\n");

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connect = pool.getConnection();
        PreparedStatement ps = null;

        //String primaryKey = "person_seq.nextval()";
        Date d = Date.valueOf(dob);

        int rowCount = 0;

        String query1 = "insert into person (fname,lname,pwd,dob,address,phone,email,cat,status,uname) values("
                + " upper(?),"
                + " upper(?),"
                + " ?,"
                + " ?,"
                + " upper(?),"
                + " upper(?),"
                + " upper(?),"
                + " ?,"
                + " ?,"
                + " ?) ";

        String query2 = "insert into instructor values("
                + " upper(?),"
                + " ?,"
                + " ?,"
                + " ?)";

        try {
            ps = connect.prepareStatement(query1);
            //ps.setString(1, primaryKey);
            ps.setString(1, fName);
            ps.setString(2, lName);
            ps.setString(3, "newpassword");
            ps.setDate(4, d);
            ps.setString(5, address);
            ps.setString(6, phone);
            ps.setString(7, email);
            ps.setInt(8, 2);
            ps.setInt(9, 0);
            ps.setString(10, uname);

            rowCount = ps.executeUpdate();

            System.out.println("*****UP TO THIS POINT*****");

            if (rowCount > 0) {
                rowCount = 0;
                System.out.println("Person " + uname + "added successfully!!!");
                Person user = getPerson(uname, 0);
                ps = connect.prepareStatement(query2);
                ps.setString(1, instructorId);
                ps.setInt(2, user.getId());
                ps.setInt(3, Integer.parseInt(dept));
                ps.setDouble(4, salary);
                rowCount = ps.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connect);
        }
        if (rowCount > 0) {
            System.out.println("Instructor " + instructorId + "added!!!");
        }
        return rowCount;
    }

    public static int addCourse(String cId, String cName, String cDesc, String dept, int crdts) {
        System.out.print("Course Information--->"
                + "\nID: " + cId
                + "\nCN: " + cName
                + "\nDESC: " + cDesc
                + "\nCR: " + crdts
                + "\nDP: " + dept + "\n");

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connect = pool.getConnection();
        PreparedStatement ps = null;

        int rowCount = 0;

        String query = "insert into course values("
                + " upper(?),"
                + " upper(?),"
                + " upper(?),"
                + " ?,"
                + " ?)";

        try {
            ps = connect.prepareStatement(query);
            //ps.setString(1, primaryKey);
            ps.setString(1, cId);
            ps.setString(2, cName);
            ps.setString(3, cDesc);
            ps.setInt(4, Integer.parseInt(dept));
            ps.setInt(5, crdts);

            rowCount = ps.executeUpdate();

            System.out.println("*****UP TO THIS POINT*****");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connect);
        }
        if (rowCount > 0) {
            System.out.println("Course " + cId + "added!!!");
        }
        return rowCount;
    }

    public static int removeStudent(String id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connect = pool.getConnection();
        Statement stmt = null;

        int rowCount = 0;

        String query = "delete from person where p_id = "
                + "(select p_id from student where s_id = '" + id + "')";

        try {
            stmt = connect.createStatement();
            rowCount = stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement(stmt);
            pool.freeConnection(connect);
        }
        if (rowCount > 0) {
            System.out.println("Student " + id + " deleted");
        }
        return rowCount;
    }

    public static int removeInstructor(String id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connect = pool.getConnection();
        Statement stmt = null;

        int rowCount = 0;

        String query = "delete from instructor where i_id = '" + id + "'";

        try {
            stmt = connect.createStatement();
            rowCount = stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement(stmt);
            pool.freeConnection(connect);
        }
        if (rowCount > 0) {
            System.out.println("Instructor " + id + " deleted");
        }
        return rowCount;
    }

    public static int addClass(String course, String student, String instructor) {
        System.out.println("\n" + "Class Information--->"
                + "\nCID: " + course
                + "\nSTD: " + student
                + "\nINSTR: " + instructor);

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connect = pool.getConnection();
        PreparedStatement ps = null;

        int rowCount = 0;

        String query = "insert into class(c_id, s_id, i_id) values("
                + " ?,"
                + " ?,"
                + " ?)";

        try {
            ps = connect.prepareStatement(query);
            //ps.setString(1, primaryKey);
            ps.setString(1, course);
            ps.setString(2, student);
            ps.setString(3, instructor);

            rowCount = ps.executeUpdate();

            System.out.println("*****UP TO THIS POINT*****");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connect);
        }
        if (rowCount > 0) {
            System.out.println("Class for " + course + " created and Student " + student + " added!!!");
        }
        return rowCount;
    }

    public static int removeClass(String id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connect = pool.getConnection();
        Statement stmt = null;

        int rowCount = 0;

        String query = "delete from class where cl_id = " + Integer.parseInt(id);

        try {
            stmt = connect.createStatement();
            rowCount = stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeStatement(stmt);
            pool.freeConnection(connect);
        }
        if (rowCount > 0) {
            System.out.println("Class " + id + " deleted!!!");
        }
        return rowCount;
    }

}
