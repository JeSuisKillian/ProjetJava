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
public class Patients {
    
    public Patients()
    {
        
    }
    
    public boolean testID(String mail)
    {
        boolean usernameExists = false;

        Connection conn;
        String URL = "jdbc:mysql://mysql-pierre-alexandre.alwaysdata.net:3306/pierre-alexandre_caresystem";
        String password ="Amoxcilline98";
        String user = "219005";
        
        try{
        conn = DriverManager.getConnection(URL, user, password);
        
        PreparedStatement st = conn.prepareStatement("select mail from Patients");
        ResultSet r1=st.executeQuery();
        String usernameCounter;
         if(r1.next()) 
         {
           usernameCounter =  r1.getString("mail");
           if(usernameCounter.equals(mail)) 
           {
              usernameExists = true;
           }
         }
         conn.close();
        
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        
        return usernameExists;
    }
    

    public void chooseAppointment()
    {
          Connection conn;
        String URL = "jdbc:mysql://mysql-pierre-alexandre.alwaysdata.net:3306/pierre-alexandre_caresystem";
        String password = "Amoxcilline98";
        String user = "219005";

        try {
            conn = DriverManager.getConnection(URL, user, password);

            PreparedStatement st1 = conn.prepareStatement("UPDATE Appointment SET Patient=?");
            st1.setString(1, m_surName);
            
            st1.execute();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
