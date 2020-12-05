/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InformationSearch;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import DAO.Appointment;
import DAO.Clinics;
import DAO.Doctors;
import DAO.Patients;

/**
 *
 * @author Pierr
 */
@SuppressWarnings("CallToPrintStackTrace")

public class InformationSearch implements InformationSearchInterface {

    private Connection conn;
    private Patients MyP;
    private Doctors MyD;
    private Clinics MyC;

    public InformationSearch(Connection c) {
            conn = c;
    }

    
    @Override
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

    @Override
    public void setPatient(String mail) {

        MyP = new Patients();
        try {
            
            PreparedStatement st = conn.prepareStatement("select*from Patients where mail = ?");
            st.setString(1, mail);
            ResultSet r1 = st.executeQuery();
            while (r1.next()) {
                MyP.setsurName(r1.getString(1));
                MyP.setfirstName(r1.getString(2));
                MyP.setAge(r1.getString(3));
                MyP.setGender(r1.getString(4));
                MyP.setMail(r1.getString(5));
                MyP.setPassword(r1.getString(6));
                MyP.setAdress(r1.getString(7));

            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
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

    @Override
    public void chargeAllAppointmentP() {

        try {
            
            PreparedStatement st = conn.prepareStatement("SELECT*FROM Appointment JOIN Patients ON Appointment.Patient = Patients.Surname WHERE Patients.Surname = ?");
            st.setString(1, MyP.getName());
            ResultSet r1 = st.executeQuery();
            while (r1.next()) {
                java.util.Date date = r1.getDate(1);
                String clinic = r1.getString(2);
                String doctor = r1.getString(3);
                String patient = r1.getString(4);
                String reason = r1.getString(5);
                boolean available = r1.getBoolean(6);
                String time = r1.getString(7);

                MyP.setApp(new Appointment(date, time, clinic, patient, doctor, reason, available));

            }
          
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean testIDDoctor(String mail) {
        boolean usernameExists = false;

        try {
            
            PreparedStatement st = conn.prepareStatement("select mail from Doctors");
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

    @Override
    public void setDoctors(String mail) {
        MyD = new Doctors();
        try {
          

            PreparedStatement st = conn.prepareStatement("select*from Doctors where mail = ?");
            st.setString(1, mail);
            ResultSet r1 = st.executeQuery();
            if (r1.next()) {
                MyD.setsurName(r1.getString(1));
                MyD.setfirstName(r1.getString(2));
                MyD.setMail(r1.getString(3));
                MyD.setPassword(r1.getString(4));
                MyD.setSpecialisation(r1.getString(5));
                MyD.setQualification(r1.getString(6));
                MyD.setInvestment(r1.getString(7));
            }

           
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean testDocPassMail(String mail, String pass) {
        boolean IndentificationOK = false;

        try {
            
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

           
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return IndentificationOK;
    }

    @Override
    public void chargeAllDocAppointment() {

        try {
           
            PreparedStatement st = conn.prepareStatement("SELECT*FROM Appointment WHERE Doctor=?");
            st.setString(1, MyD.getDocName());
            ResultSet r1 = st.executeQuery();
            while (r1.next()) {
                java.util.Date date = r1.getDate(1);
                String time = r1.getString(7);
                String clinic = r1.getString(2);
                String doctor = r1.getString(3);
                String patient = r1.getString(4);
                String reason = r1.getString(5);
                boolean available = r1.getBoolean(6);

                MyD.setAppDoc(new Appointment(date, time, clinic, patient, doctor, reason, available));

            }
         
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setClinic() {

        MyC = new Clinics();
        try {

            
            PreparedStatement st = conn.prepareStatement("select name from Clinics");
            ResultSet r1 = st.executeQuery();
            while (r1.next()) {
                MyC.setNameCl(r1.getString(1));

            }

          
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void chargeAllAppointmentC(String name) {

        try {
            
            PreparedStatement st = conn.prepareStatement("SELECT*FROM Appointment WHERE Clinic=?");
            st.setString(1, name);
            ResultSet r1 = st.executeQuery();

            while (r1.next()) {

                java.util.Date date = r1.getDate(1);
                String time = r1.getString(7);
                String clinic = r1.getString(2);
                String doctor = r1.getString(3);
                String patient = r1.getString(4);
                String reason = r1.getString(5);
                boolean available = r1.getBoolean(6);

                MyC.setAppH(new Appointment(date, time, clinic, patient, doctor, reason, available));

            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList getInfoP() {
        ArrayList<String> info = new ArrayList<>();

        try {
           
            PreparedStatement st = conn.prepareStatement("SELECT MAIL FROM Patients");
            ResultSet r1 = st.executeQuery();

            while (r1.next()) {

                String mail = r1.getString("MAIL");
                info.add(mail);
            }
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return info;
    }

    @Override
    public ArrayList NameDoctor() {
        ArrayList<String> nameDoc = new ArrayList<>();

        try {
            
            PreparedStatement st = conn.prepareStatement("SELECT Surname FROM Doctors");
            ResultSet r1 = st.executeQuery();

            while (r1.next()) {
                nameDoc.add(r1.getString(1));

            }
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nameDoc;
    }

    public Patients getP() {
        return MyP;
    }

    public Clinics getC() {
        return MyC;
    }

    @Override
    public boolean checkDocH(String doc, String clinic) {
        boolean DocHMatches = true;

        try {
            
            PreparedStatement st = conn.prepareStatement("SELECT*FROM Doctors WHERE(SURNAME=? AND (Clinic=? OR Clinic2=?))");
            st.setString(1, doc);
            st.setString(2, clinic);
            st.setString(3, clinic);
            ResultSet r1 = st.executeQuery();

            if (r1.next() == false) {
                DocHMatches = false;
                JOptionPane.showMessageDialog(null, "This doctor is not availbale in this clinic, please pick an other one");
            }

          
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return DocHMatches;
    }

    public boolean checkHour(Appointment App) {
        boolean checkHour = false;

        for (int i = 0; i < MyD.getDocApp().size(); ++i) {
            if (MyD.getDocApp(i).getTime().equals(App.getTime())
                    && MyD.getDocApp(i).getDate().equals(App.getDate())) {
                System.out.println("AH");
                checkHour = true;
            }
        }
        return checkHour;
    }

    @Override
    public String returnMailDoc(String name) {
        String mail = " ";

        try {
            
            PreparedStatement st = conn.prepareStatement("select mail from Doctors where surname=?");
            st.setString(1, name);
            ResultSet r1 = st.executeQuery();

            while (r1.next()) {
                mail = r1.getString(3);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(mail);
        return mail;
    }

    @Override
    public String returnNameFullP(String mail) {
        String surname = " ", firstName = " ", name;

        try {
           
            PreparedStatement st = conn.prepareStatement("select surname from Patients  where mail=?");
            PreparedStatement st1 = conn.prepareStatement("select firstname from Patients  where mail=?");
            st.setString(1, mail);
            st1.setString(1, mail);
            ResultSet r1 = st.executeQuery();
            ResultSet r2 = st1.executeQuery();

            while (r1.next() && r2.next()) {
                surname = r1.getString(1);
                firstName = r2.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        name = surname + " " + firstName;
        System.out.println(name);
        return name;
    }

    @Override
    public ArrayList returnSurnameP() {

        ArrayList<String> temp = new ArrayList<>();

        try {
            
            PreparedStatement st = conn.prepareStatement("select surname from Patients");
            ResultSet r1 = st.executeQuery();
           

            while (r1.next()) {
                String surname = r1.getString(1);

                temp.add(surname);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }

    @Override
    public String returnPatientMail(String name) {
        String mail = " ";

        try {
            
            PreparedStatement st = conn.prepareStatement("select mail from Patients where surname=?");
            st.setString(1, name);
            ResultSet r1 = st.executeQuery();

            while (r1.next()) {
                mail = r1.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(mail);
        return mail;
    }

    @Override
    public ArrayList AdvancedResearch(String reason) {
        ArrayList<String> temp = new ArrayList<>();
        try {
            
            PreparedStatement st = conn.prepareStatement("SELECT DISTINCT surname FROM Patients JOIN Appointment ON Patients.Surname = Appointment.Patient WHERE Appointment.Reason = ?");
            st.setString(1, reason);
            ResultSet r1 = st.executeQuery();

            while (r1.next()) {
                temp.add(r1.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public Doctors getD() {
        return MyD;
    }
}