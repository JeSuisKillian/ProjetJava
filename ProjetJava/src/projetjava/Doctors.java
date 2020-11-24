/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author Pierr
 */
public class Doctors {

    private String m_surName;
    private String m_firstName;
    private String m_mail;
    private String m_password;
    private String m_specialisation;
    private String m_qualification;
    private String m_investement;
    private ArrayList<Appointment> m_DocApp = new ArrayList<>();

    public Doctors() {

    }

    public boolean testIDDoctor(String mail) {
        boolean usernameExists = false;

        Connection conn;
        String URL = "jdbc:mysql://mysql-pierre-alexandre.alwaysdata.net:3306/pierre-alexandre_caresystem";
        String password = "Amoxcilline98";
        String user = "219005";

        try {
            conn = DriverManager.getConnection(URL, user, password);

            PreparedStatement st = conn.prepareStatement("select mail from Doctors");
            ResultSet r1 = st.executeQuery();
            String usernameCounter;
            if (r1.next()) {
                usernameCounter = r1.getString("mail");
                if (usernameCounter.equals(mail)) {
                    usernameExists = true;
                }
            }
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usernameExists;
    }

    public void setDoctors(String mail) {
        Connection conn;
        String URL = "jdbc:mysql://mysql-pierre-alexandre.alwaysdata.net:3306/pierre-alexandre_caresystem";
        String password = "Amoxcilline98";
        String user = "219005";

        try {
            conn = DriverManager.getConnection(URL, user, password);

            PreparedStatement st = conn.prepareStatement("select*from Doctors where mail = ?");
            st.setString(1, mail);
            ResultSet r1 = st.executeQuery();
            if (r1.next()) {
                m_surName = r1.getString(1);
                m_firstName = r1.getString(2);
                m_mail = r1.getString(3);
                m_password = r1.getString(4);
                m_specialisation = r1.getString(5);
                m_qualification = r1.getString(6);
                m_investement = r1.getString(7);
            }
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addDoctors(String surName, String firstName, String mail,
            String pass, String specialisation, String qualification, String investment) {
        if (testIDDoctor(mail) == false) {
            Connection conn;
            String URL = "jdbc:mysql://mysql-pierre-alexandre.alwaysdata.net:3306/pierre-alexandre_caresystem";
            String password = "Amoxcilline98";
            String user = "219005";
            try {
                conn = DriverManager.getConnection(URL, user, password);
                String sql = "insert into Doctors (SURNAME, FIRSTNAME, MAIL,PASSWORD, SPECIALISATIONS, QUALIFICATIONS,INVESTMENT) values (?,?,?,?,?,?,?)";
                         
                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1, surName);
                st.setString(2, firstName);
                st.setString(3, mail);
                st.setString(4, pass);
                st.setString(5, specialisation);
                st.setString(6, qualification);
                st.setString(7, investment);
                st.execute();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean testDocPassMail(String mail, String pass) {
        boolean IndentificationOK = false;

        Connection conn;
        String URL = "jdbc:mysql://mysql-pierre-alexandre.alwaysdata.net:3306/pierre-alexandre_caresystem";
        String password = "Amoxcilline98";
        String user = "219005";

        try {
            conn = DriverManager.getConnection(URL, user, password);

            PreparedStatement st1 = conn.prepareStatement("select mail from Doctors");
            PreparedStatement st2 = conn.prepareStatement("select password from Doctors");
            ResultSet r1 = st1.executeQuery();
            String usernameMail;
            System.out.println(mail);
            System.out.println(pass);
            ResultSet r2 = st2.executeQuery();
            String usernamePass;
            while (r1.next() && r2.next()) {
                usernameMail = r1.getString("mail");
                System.out.println(usernameMail);
                usernamePass = r2.getString("password");
                System.out.println(usernamePass);
                if (usernameMail.equals(mail) && usernamePass.equals(pass)) {
                    IndentificationOK = true;
                    System.out.println("Bon mail et mdp");
                    return IndentificationOK;
                }
            }
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return IndentificationOK;
    }
    
    public void chargeAllDocAppointment() {
        Connection conn;
        String URL = "jdbc:mysql://mysql-pierre-alexandre.alwaysdata.net:3306/pierre-alexandre_caresystem";
        String password = "Amoxcilline98";
        String user = "219005";

        try {
            conn = DriverManager.getConnection(URL, user, password);
            PreparedStatement st = conn.prepareStatement("SELECT*FROM Appointment WHERE Doctor=?");
            st.setString(1, m_surName);
            ResultSet r1 = st.executeQuery();
            while (r1.next()) {
                Date date = r1.getDate(1);
                String time = r1.getString(7);
                String clinic = r1.getString(2);
                String doctor = r1.getString(3);
                String patient = r1.getString(4);
                String reason = r1.getString(5);
                boolean available = r1.getBoolean(6);

                m_DocApp.add(new Appointment(date, time,clinic, patient, doctor, reason, available));

            }
            System.out.println(m_DocApp.get(0).getPatient());
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
