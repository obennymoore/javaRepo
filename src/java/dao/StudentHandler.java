/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import data.ConnectionPool;
import data.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import rest.model.StudentDAO;

/**
 *
 * @author OBENNY
 */
public class StudentHandler {
    
    public static StudentDAO getStudent(String id)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connect = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        StudentDAO student = new StudentDAO();
        
        String query = "select s_id,fname,lname,dob,address,phone,email,cat,status,uname,d_id,i_id \n" +
                        "from person p join student s on s.p_id = p.p_id join department d on d.d_id = s.d_id\n" +
                        "where s_id = ?";
        
        try
        {
            ps = connect.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            
            if(rs.next())
            {
                
                student.setAddress(rs.getString("address"));
                student.setCategory(rs.getInt("cat"));
                student.setDob(rs.getDate("dob").toString());
                student.setEmail(rs.getString("email"));
                student.setFirstName(rs.getString("fname"));
                student.setLastName(rs.getString("lname"));
                student.setPhone(rs.getString("phone"));
                student.setStatus(rs.getInt("status"));
                student.setuName(rs.getString("uname"));
                student.setStudentId(rs.getString("s_id"));
                student.setDept(rs.getString("d_id"));
                student.setAdvisor(rs.getString("i_id"));
                

            }
            
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connect);
        }
        return student;
    }
    
}
