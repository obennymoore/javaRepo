/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import data.ConnectionPool;
import data.DBUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import rest.model.DepartmentDAO;

/**
 *
 * @author OBENNY
 */
public class DepartmentHandler {
    public static List<DepartmentDAO> getDepartments()
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connect = pool.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        
        List<DepartmentDAO> depts = new ArrayList<DepartmentDAO> ();
        
        String query = "select d_id,d_code,d_name from department";
        
        try
        {
            stmt = connect.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next())
            {
                DepartmentDAO dept = new DepartmentDAO();
                dept.setDeptId(rs.getInt("d_id"));
                dept.setDeptCode(rs.getString("d_code"));
                dept.setDeptName(rs.getString("d_name"));
                depts.add(dept);
            }
            return depts;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            DBUtil.closeResultSet(rs);
            DBUtil.closeStatement(stmt);
            pool.freeConnection(connect);
        } 
    }
    
}
