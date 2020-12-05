/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

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
    private ArrayList<String> m_name = new ArrayList<>();
    private String m_adress;
    private ArrayList<Appointment> m_ClApp = new ArrayList<>();
    
    public Clinics()
    {
        
    }
    
    
    public ArrayList getAppH() {
        return m_ClApp;
    }

    public Appointment getAppH(int i) {
        return m_ClApp.get(i);
    }
    public void setNameCl(String name)
    {
        m_name.add(name);
    }
    public void setAppH(Appointment App)
    {
        m_ClApp.add(App);
    }
    public int getNameClinicSize()
    {
        return m_name.size();
    }
            
            
    public String getClinicName(int i)
    {
        return m_name.get(i);
    }
}
