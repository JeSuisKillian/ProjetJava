/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author Pierr
 */
public class Doctors extends Users {

    private String m_specialisation;
    private String m_qualification;
    private String m_investement;
    private ArrayList<Appointment> m_DocApp = new ArrayList<>();

    public Doctors() {

    }

    public void setsurName(String name)
    {
        m_surName=name;
    }
    public void setfirstName(String name)
    {
        m_firstName=name;
    }
  
    public void setMail(String mail)
    {
        m_mail=mail;
    }
    public void setPassword(String password)
    {
        m_password = password;
    }


   public void setSpecialisation (String specialisation)
    {
        m_specialisation = specialisation;
    }

    public void setQualification (String qualification)
    {
        m_qualification = qualification;
    }

    public void setInvestment (String Investement)
    {
        m_investement = Investement;
    }
    public void setAppDoc(Appointment App)
    {
        m_DocApp.add(App);
    }
    public String getDocName()
    {
        return m_surName;
    }
    public ArrayList getDocApp()
    {
        return m_DocApp;
    }
    public Appointment getDocApp(int i)
    {
        return m_DocApp.get(i);
    }

    

   
    
   
}
