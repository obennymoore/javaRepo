/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import java.sql.*;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author OBENNY
 */
public class ConnectionPool {
    
    private static ConnectionPool pool = null;
    private static DataSource dataSource = null;
    
    private ConnectionPool()
    {
        try
        {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/detech");
            System.out.println("Connection Pool Object Created!");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static ConnectionPool getInstance()
    {
        if(pool == null)
        {
            pool = new ConnectionPool();
        }
        return pool;
    }
    
    public Connection getConnection()
    {
        try
        {
            return dataSource.getConnection();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public void freeConnection(Connection c)
    {
        try
        {
            c.close();
            System.out.println("Connection Pool Object Freed!");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}
 