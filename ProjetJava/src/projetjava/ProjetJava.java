/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava;

import java.sql.*;

/**
 *
 * @author killi
 */
public class ProjetJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Branche Commune ?");
        Connection conn = null;
        
        try {
            // db parameters
            String url = "jdbc:mysql://localhost:3306/bdd";
            String user = "root";
            String password = "";
            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from table1");
            while (rs.next()) {
                System.out.println(rs.getInt(1));
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("xyz" + e.getMessage());
        }
    }

}
