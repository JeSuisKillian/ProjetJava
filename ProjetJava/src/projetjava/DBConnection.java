/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava;
import java.sql.*;

/**
 *
 * @author Pierr
 */
public class DBConnection {
    
    private Connection conn;
    
    public DBConnection()
    {
        
    }
    
   public Connection getConnection()  {
    try{
        
        String URL = "jdbc:mysql://mysql-pierre-alexandre.alwaysdata.net:3306/pierre-alexandre_caresystem";
        String password = "Amoxcilline98";
        String user = "219005";
       conn = DriverManager.getConnection(URL, user, password);
    }
    catch(SQLException e)
    {
        e.printStackTrace();
    }
    return conn;
  }
}
