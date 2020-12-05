/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataUpdate;

import InformationSearch.InformationSearch;
import java.sql.*;
import projetjava.DBConnection;

/**
 *
 * @author Pierr
 */
@SuppressWarnings("CallToPrintStackTrace")

public class DataUpdate implements DataUpdateInterface {

    private Connection conn;
    private final InformationSearch MyI;

    public DataUpdate(InformationSearch I) {
        MyI = I;

    }

    @Override
    public void addPatients(String surName, String firstName, String age, String gender,
            String mail, String password) {
        if (MyI.testIDPatient(mail) == false) {

            try {
                conn = new DBConnection().getConnection();
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
                conn.close();
                System.out.println("Added Patient");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void chooseAppointment(String doc, String time, String Clinic, String reason, java.util.Date date) {

        try {
            conn = new DBConnection().getConnection();
            java.sql.Date date2 = new java.sql.Date(date.getTime());
            PreparedStatement st1 = conn.prepareStatement("INSERT INTO Appointment(Patient, Doctor, Clinic,Hour, Reason,Date)values (?,?,?,?,?,?)");
            st1.setString(1, MyI.getP().getName());
            System.out.println("ok");
            st1.setString(2, doc);
            st1.setString(3, Clinic);
            st1.setString(4, time);
            st1.setString(5, reason);
            st1.setDate(6, date2);
            st1.execute();

            PreparedStatement st2 = conn.prepareStatement("UPDATE Appointment SET Available=0 WHERE Patient IS NOT NULL");
            st2.execute();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addDoctors(String surName, String firstName, String mail,
            String pass, String specialisation, String qualification, String investment, String clinic, String clinic2) {
        if (MyI.testIDDoctor(mail) == false) {

            try {
                conn = new DBConnection().getConnection();
                String sql = "insert into Doctors (SURNAME, FIRSTNAME, MAIL,PASSWORD, SPECIALISATIONS, QUALIFICATIONS,INVESTMENT,CLINIC,Clinic2) values (?,?,?,?,?,?,?,?,?)";

                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1, surName);
                st.setString(2, firstName);
                st.setString(3, mail);
                st.setString(4, pass);
                st.setString(5, specialisation);
                st.setString(6, qualification);
                st.setString(7, investment);
                st.setString(8, clinic);
                st.setString(9, clinic2);
                st.execute();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public void DeletePatient(String mail)
    {
        try {
            conn = new DBConnection().getConnection();
            String sql = "DELETE from Patients where mail=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, mail);
            st.execute();
            conn.close();
            System.out.println("Patient deleted");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void DeleteDoctor(String mail)
    {
        try {
            conn = new DBConnection().getConnection();
            String sql = "DELETE from Doctors where mail=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, mail);
            st.execute();
            conn.close();
            System.out.println("Doctor deleted");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePatient(String surName, String firstName, String age, String adress,
            String gender, String mail) {
        try {
            conn = new DBConnection().getConnection();
            String sql = "UPDATE Patients SET surname=?, firstname=?, age=?, gender=?, adresse=? WHERE mail =?";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, surName);
            st.setString(2, firstName);
            st.setString(3, age);
            st.setString(4, gender);
            st.setString(5, adress);
            st.setString(6, mail);
            st.execute();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    

}
