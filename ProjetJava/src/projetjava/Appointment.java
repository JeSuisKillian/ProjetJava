/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava;

import java.sql.*;
import java.util.GregorianCalendar;

/**
 *
 * @author killi
 */
public class Appointment {

    private GregorianCalendar date;
    private Patients patient;
    private Doctors doctor;
    private String motif;
    private boolean disponible;

    public Appointment(GregorianCalendar d, Doctors doc, String m, boolean dispo) {
        date = d;
        doctor = doc;
        motif = m;
        disponible = dispo;
    }

    public void CreateApp() {
        Connection conn;
        String URL = "jdbc:mysql://mysql-pierre-alexandre.alwaysdata.net:3306/pierre-alexandre_caresystem";
        String password = "Amoxcilline98";
        String user = "219005";

        try {
            conn = DriverManager.getConnection(URL, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    
}
