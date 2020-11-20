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

    private String m_surName;
    private String m_firstName;
    private String m_age;
    private String m_gender;
    private String m_mail;
    private String m_password;
    private String m_adress;

    public Patients() {

    }

    public boolean testIDPatient(String mail) {
        boolean usernameExists = false;

        Connection conn;
        String URL = "jdbc:mysql://mysql-pierre-alexandre.alwaysdata.net:3306/pierre-alexandre_caresystem";
        String password = "Amoxcilline98";
        String user = "219005";

        try {
            conn = DriverManager.getConnection(URL, user, password);

            PreparedStatement st = conn.prepareStatement("select mail from Patients");
            ResultSet r1 = st.executeQuery();
            String usernameCounter;
            while (r1.next()) {
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

    public void setPatient(String mail) {
        Connection conn;
        String URL = "jdbc:mysql://mysql-pierre-alexandre.alwaysdata.net:3306/pierre-alexandre_caresystem";
        String password = "Amoxcilline98";
        String user = "219005";

        try {
            conn = DriverManager.getConnection(URL, user, password);

            PreparedStatement st = conn.prepareStatement("select*from Patients where mail = ?");
            st.setString(1, mail);
            ResultSet r1 = st.executeQuery();
            if (r1.next()) {
                m_surName = r1.getString(1);
                m_firstName = r1.getString(2);
                m_age = r1.getString(3);
                m_gender = r1.getString(4);
                m_mail = r1.getString(5);
                m_password = r1.getString(6);

            }
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPatients(String surName, String firstName, String age, String gender,
            String mail, String password) {
        if (testIDPatient(mail) == false) {
            Connection conn;
            String URL = "jdbc:mysql://mysql-pierre-alexandre.alwaysdata.net:3306/pierre-alexandre_caresystem";
            String pass = "Amoxcilline98";
            String user = "219005";
            try {
                conn = DriverManager.getConnection(URL, user, pass);
                String sql = "insert into Patients (SURNAME, FIRSTNAME, AGE, GENDER, MAIL,PASSWORD) values (?,?,?,?,?,?)";
                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1, surName);
                st.setString(2, firstName);
                st.setString(3, age);
                st.setString(4, gender);
                st.setString(5, mail);
                st.setString(6, password);
                st.execute();
                conn.close();
                System.out.println("Added Patient");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean testPassMail(String mail, String pass) {
        boolean IndentificationOK = false;

        Connection conn;
        String URL = "jdbc:mysql://mysql-pierre-alexandre.alwaysdata.net:3306/pierre-alexandre_caresystem";
        String password = "Amoxcilline98";
        String user = "219005";

        try {
            conn = DriverManager.getConnection(URL, user, password);

            PreparedStatement st1 = conn.prepareStatement("select mail from Patients");
            PreparedStatement st2 = conn.prepareStatement("select password from Patients");
            ResultSet r1 = st1.executeQuery();
            String usernameMail;
            System.out.println(mail);
            System.out.println(pass);
            ResultSet r2 = st2.executeQuery();
            String usernamePass;
            while (r1.next() && r2.next()) 
            {
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
    
    public void chooseAppointment() {
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
