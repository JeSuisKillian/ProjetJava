/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava;

import java.sql.*;
import java.util.ArrayList;

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

            PreparedStatement st = conn.prepareStatement("select mail from doctors");
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
                            String pass, String specialisation, String qualification, String investment)
    {
        if (testIDDoctor(mail) == false) {
            Connection conn;
            String URL = "jdbc:mysql://mysql-pierre-alexandre.alwaysdata.net:3306/pierre-alexandre_caresystem";
            String password = "Amoxcilline98";
            String user = "219005";

            try {
                conn = DriverManager.getConnection(URL, user, password);
               String sql = "insert into Doctors (SURNAME, FIRSTNAME, MAIL,PASSWORD, SPECIALISATIONS, QUALIFICATIONS"
                       + "INVESTMENT) values (?,?,?,?,?,?,?)";
                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1, surName);
                st.setString(2, firstName);
                st.setString(3, mail);
                st.setString(4, pass);
                st.setString(5, specialisation);
                st.setString(6, qualification);
                st.setString(6, investment);
                st.execute();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}
    
    public void addAppointement()
    {
        //ajouter un nouveau rendez vous dans la base de donnée dont c'est lui le docteur
    }
    

}