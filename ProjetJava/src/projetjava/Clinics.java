/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author tombe
 */
public class Clinics {
    private String m_name;
    private String m_adress;
    private ArrayList<Appointment> m_ClApp = new ArrayList<>();
    
    public Clinics()
    {
        
    }
    
    public void setClinic(String n) {
        Connection conn;
        String URL = "jdbc:mysql://mysql-pierre-alexandre.alwaysdata.net:3306/pierre-alexandre_caresystem";
        String password = "Amoxcilline98";
        String user = "219005";
      
        try {
            conn = DriverManager.getConnection(URL, user, password);

            PreparedStatement st = conn.prepareStatement("select*from Clinics where name = ?");
            st.setString(1, n);
            ResultSet r1 = st.executeQuery();
            while (r1.next()) {
                m_name = r1.getString(1);
                m_adress = r1.getString(2);
               
            }
            conn.close();
          
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void chargeAllAppointment(String name)
    {
         Connection conn;
        String URL = "jdbc:mysql://mysql-pierre-alexandre.alwaysdata.net:3306/pierre-alexandre_caresystem";
        String password = "Amoxcilline98";
        String user = "219005";

        try {
            conn = DriverManager.getConnection(URL, user, password);
            PreparedStatement st= conn.prepareStatement("SELECT*FROM Appointment WHERE Clinic=?");
            st.setString(1, name);
            ResultSet r1 = st.executeQuery();
            
            while(r1.next())
            {
                
                Date date=r1.getDate(1);
                String time=r1.getString(7);
                String clinic=r1.getString(2);
                String doctor=r1.getString(3);
                String patient=r1.getString(4);
                String reason=r1.getString(5);
                boolean available=r1.getBoolean(6);
                
                m_ClApp.add(new Appointment(date, time, clinic, patient, doctor, reason, available));
                
            }
           
            conn.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    public ArrayList getAppH() {
        return m_ClApp;
    }

    public Appointment getAppH(int i) {
        return m_ClApp.get(i);
    }
}
