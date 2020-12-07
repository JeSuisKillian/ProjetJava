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
import java.util.*;

/**
 *
 * @author Pierr
 */
public class Patients extends Users{

    private String m_age;
    private String m_gender;
    private String m_adress;
    private ArrayList<Appointment> m_PaApp = new ArrayList<>();

    public Patients() {

    }

    public void setsurName(String name) {
        m_surName = name;
    }

    public void setfirstName(String name) {
        m_firstName = name;
    }

    public void setAge(String age) {
        m_age = age;
    }

    public void setGender(String gender) {
        m_gender = gender;
    }

    public void setMail(String mail) {
        m_mail = mail;
    }

    public void setPassword(String password) {
        m_password = password;
    }

    public void setAdress(String adress) {
        m_adress = adress;
    }

    public void setApp(Appointment App) {
        m_PaApp.add(App);
    }

     public String getFirstName() {
        return m_firstName;
    }
    public String getName() {
        return m_surName;
    }
    public String getFullName(){
        String fName=m_surName + " " + m_firstName;
        return fName;
    }
     public String getAge() {
        return m_age;
    }
     public String getGender()
     {
         return m_gender;
     }
      public String getMail() {
        return m_mail;
    }
      public String getAdress() {
        return m_adress;
    }

    public ArrayList getApp() {
        return m_PaApp;
    }

    public Appointment getApp(int i) {
        return m_PaApp.get(i);
    }

}
