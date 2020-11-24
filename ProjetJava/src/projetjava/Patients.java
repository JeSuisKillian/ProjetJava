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
import java.util.*;

/**
 *
 * @author Pierr
 */
public class Patients {

    private String m_surName;
    private String m_firstName;
    private String m_age;
    private String m_gender;
    private String m_mail;
    private String m_password;
    private String m_adress;
    private ArrayList<Appointment> m_PaApp = new ArrayList<>();
    private Connection conn;

    public Patients() {
        try {
            conn = new DBConnection().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean testIDPatient(String mail) {
        boolean usernameExists = false;

        try {

            PreparedStatement st = conn.prepareStatement("select mail from Patients");
            ResultSet r1 = st.executeQuery();
            String usernameCounter;
            while (r1.next()) {
                usernameCounter = r1.getString("mail");
                if (usernameCounter.equals(mail)) {
                    usernameExists = true;
                }
            }
            

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usernameExists;
    }

    public void setPatient(String mail) {

        try {
            PreparedStatement st = conn.prepareStatement("select*from Patients where mail = ?");
            st.setString(1, mail);
            ResultSet r1 = st.executeQuery();
            while (r1.next()) {
                m_surName = r1.getString(1);
                m_firstName = r1.getString(2);
                m_age = r1.getString(3);
                m_gender = r1.getString(4);
                m_mail = r1.getString(5);
                m_password = r1.getString(6);

            }    

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPatients(String surName, String firstName, String age, String gender,
            String mail, String password) {
        if (testIDPatient(mail) == false) {

            try {

                String sql = "insert into Patients (SURNAME, FIRSTNAME, AGE, GENDER, MAIL,PASSWORD,ADRESSE) values (?,?,?,?,?,?,?)";
                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1, surName);
                st.setString(2, firstName);
                st.setString(3, age);
                st.setString(4, gender);
                st.setString(5, mail);
                st.setString(6, password);
                st.setString(7, "ici");
                st.execute();
                
                System.out.println("Added Patient");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean testPassMail(String mail, String pass) {
        boolean IndentificationOK = false;

        try {

            PreparedStatement st1 = conn.prepareStatement("select mail from Patients");
            PreparedStatement st2 = conn.prepareStatement("select password from Patients");
            ResultSet r1 = st1.executeQuery();
            String usernameMail;
            ResultSet r2 = st2.executeQuery();
            String usernamePass;
            while (r1.next() && r2.next()) {
                usernameMail = r1.getString("mail");
                usernamePass = r2.getString("password");
                if (usernameMail.equals(mail) && usernamePass.equals(pass)) {
                    IndentificationOK = true;
                    System.out.println("Bon mail et mdp");
                    return IndentificationOK;
                }
            }
            

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return IndentificationOK;
    }

    public void chooseAppointment(String doc, String time, String Clinic, String reason, Date date) {

        try {
           java.sql.Date date2 = new java.sql.Date(date.getTime());
            PreparedStatement st1 = conn.prepareStatement("INSERT INTO Appointment(Patient, Doctor, Clinic,Hour, Reason,Date)values (?,?,?,?,?,?)");
            st1.setString(1, m_surName);
            st1.setString(2, doc);
            st1.setString(3,Clinic);
            st1.setString(4,time);
            st1.setString(5, reason);
            st1.setDate(6,date2);
            st1.execute();

            PreparedStatement st2 = conn.prepareStatement("UPDATE Appointment SET Available=0 WHERE Patient IS NOT NULL");
            st2.execute();
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void chargeAllAppointment() {

        try {

            PreparedStatement st = conn.prepareStatement("SELECT*FROM Appointment WHERE Patient=?");
            st.setString(1, m_surName);
            ResultSet r1 = st.executeQuery();
            while (r1.next()) {
                Date date = r1.getDate(1);
                String clinic = r1.getString(2);
                String doctor = r1.getString(3);
                String patient = r1.getString(4);
                String reason = r1.getString(5);
                boolean available = r1.getBoolean(6);
                String time = r1.getString(7);

                m_PaApp.add(new Appointment(date, time, clinic, patient, doctor, reason, available));

            }
            
            System.out.println(m_PaApp.get(0).getDate());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList getApp() {
        return m_PaApp;
    }

    public Appointment getApp(int i) {
        return m_PaApp.get(i);
    }

    public void fermeture()
    {
        try{
        conn.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}
