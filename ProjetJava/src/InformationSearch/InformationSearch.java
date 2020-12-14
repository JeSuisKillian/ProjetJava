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

/**
 * Classe permettant de consulter la base de données pour des recherches
 * d'informations
 *
 * @implements DataUpdateInterface
 */
public class InformationSearch implements InformationSearchInterface {

    private Connection conn;
    private Patients MyP;
    private Doctors MyD;
    private Clinics MyC;

    /**
     * Constructeur
     *
     * @param c la conncection
     * @return InformationSearch : l'objet créé
     */
    public InformationSearch(Connection c) {
        conn = c;
    }

    /**
     * Méthode permettant de vérifier que le mail rentré par l'utilisateur
     * existe dans la base de données
     *
     * @param mail : rentré par l'utilisateur
     * @return true : si le mail existe bien dans la BDD
     */
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

    /**
     * Méthode permettant d'affecter l'attribut Patient de la classe
     * InformationSearch
     *
     * @param mail : du patient séléctionné
     *
     */
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

    /**
     * Méthode permettant de vérifier que le mot de passe entré correspond au
     * mail entré par le patient
     *
     * @param mail, pass : mail et mot de passe pour vérifier qu'ils se
     * correspondent
     * @return true : si le mot de passe est le bon
     */
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

                    return IndentificationOK;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return IndentificationOK;
    }

    /**
     * Méthode permettant de charger dans le Patient tous ses rendez-vous
     * médicaux
     *
     *
     */
    @Override
    public void chargeAllAppointmentP() {

        try {

            PreparedStatement st = conn.prepareStatement("SELECT*FROM Appointment JOIN Patients ON Appointment.Patient = Patients.Surname WHERE Patients.Surname = ? ORDER BY DATE");
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

    /**
     * Méthode permettant de vérifier que le mail rentré par l'utilisateur
     * existe dans la base de données
     *
     * @param mail : rentré par l'utilisateur
     * @return true : si le mail existe bien dans la BDD
     */
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

    /**
     * Méthode permettant d'affecter l'attribut Doctor de la classe
     * InformationSearch
     *
     * @param mail : du docteur séléctionné
     *
     */
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

    /**
     * Méthode permettant de vérifier que le mot de passe entré correspond au
     * mail entré par le docteur
     *
     * @param mail, pass : mail et mot de passe pour vérifier qu'ils se
     * correspondent
     * @return true : si le mot de passe est le bon
     */
    @Override
    public boolean testDocPassMail(String mail, String pass) {
        boolean IndentificationOK = false;

        try {

            PreparedStatement st1 = conn.prepareStatement("select mail from Doctors");
            PreparedStatement st2 = conn.prepareStatement("select password from Doctors");
            ResultSet r1 = st1.executeQuery();
            String usernameMail;

            ResultSet r2 = st2.executeQuery();
            String usernamePass;
            while (r1.next() && r2.next()) {
                usernameMail = r1.getString("mail");

                usernamePass = r2.getString("password");

                if (usernameMail.equals(mail) && usernamePass.equals(pass)) {
                    IndentificationOK = true;

                    return IndentificationOK;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return IndentificationOK;
    }

    /**
     * Méthode permettant de charger dans l Doctor tous ses rendez-vous médicaux
     *
     *
     */
    @Override
    public void chargeAllDocAppointment() {

        try {

            PreparedStatement st = conn.prepareStatement("SELECT*FROM Appointment WHERE Doctor=? ORDER BY DATE");
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

    /**
     * Affecter l'attribut clinic de InformationSearch
     *
     *
     */
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

    /**
     * Méthode permettant de charger dans la Clinique tous ses rendez-vous
     *
     *
     */
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

    /**
     * Méthode permettant de récupérer toutes les informationdans du patient
     * séléctionné
     *
     * @return info : un tableau de chaînes de caractères contenant chacune une
     * donnée du Patient (exemple : son adresse, son âge etc.)
     *
     */
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

    /**
     * Méthode permettant de récupérer tous les noms des docteurs pour les
     * charger dans une comboBox
     *
     * @return nameDoc : un tableau de chaînes de caractères contenant chacune
     * un nom de docteur
     */
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

    /**
     * Méthode permettant de vérifier qu'un docteur travaille dans une clinique
     * donnée
     *
     * @param doc, clinic : pour vérifier s'ils se correspondent
     * @return true : si "doc" appartient bien à la clinique "clinic"
     */
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

    /**
     * Lorsque qu'un rdv est pris avec un certain docteur, cette méthode permet
     * de vérifier n'a pas déjà un rendez-vous à la même heure
     *
     * @param App : le rendez-vous séléctionné par le patient
     * @return true : si le docteur séléctionné est libre
     */
    public boolean checkHour(Appointment App) {
        boolean checkHour = false;

        for (int i = 0; i < MyD.getDocApp().size(); ++i) {
            if (MyD.getDocApp(i).getTime().equals(App.getTime())
                    && MyD.getDocApp(i).getDate().equals(App.getDate())) {

                checkHour = true;
            }
        }
        return checkHour;
    }

    /**
     * Méthode permettant récupérer un mail d'un docteur via son prénom
     *
     * @param name : le nom du docteur
     * @return mail : le mail du docteur
     */
    @Override
    public String returnMailDoc(String name) {
        String mail = " ";

        try {

            PreparedStatement st = conn.prepareStatement("select mail from Doctors where surname=?");
            st.setString(1, name);
            ResultSet r1 = st.executeQuery();

            while (r1.next()) {
                mail = r1.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mail;
    }

    /**
     * Méthode permettant de récupérer le nom complet un patient
     *
     * @param mail : identifiant du patient
     * @return name : nom complet du patient
     */
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

        return name;
    }

    /**
     * Méthode permettant de récupérer le nom de tous les patients pour les
     * charger dans une comboBox
     *
     * @return temp : un tableau de chaînes de caractères contenant chacune un
     * nom de patient
     */
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

    /**
     * Méthode permettant récupérer le mail d'un patient via son nom de famille
     *
     * @param name : le nom du patient
     * @return mail : le mail du patient
     */
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

        return mail;
    }

    /**
     * Méthode permettant récupérer le mail d'un docteur via son nom de famille
     *
     * @param name : le nom du docteur
     * @return mail : le mail du docteur
     */
    public String returnDoctorMail(String name) {
        String mail = " ";

        try {

            PreparedStatement st = conn.prepareStatement("select mail from Doctors where surname=?");
            st.setString(1, name);
            ResultSet r1 = st.executeQuery();

            while (r1.next()) {
                mail = r1.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mail;
    }

    /**
     * Méthode permettant de faire une recherche de patient un motif pour l'un
     * de ses rendez-vous (exemple : quels patients ont consulté pour une
     * entorse ?)
     *
     * @param reason : le motif en question
     * @return temp : un tableau de chaîne de caractères contenant chacune le
     * nom d'un patient qui a pris un rendez-vous pour ce motif
     */
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

    /**
     * Méthode permettant vider le tableau de rendez-vous du patient selectionné
     * (pour éviter les doublons au moment de la sélection des rendez-vous)
     *
     *
     */
    public void clear() {
        MyP.getApp().clear();
    }

    /**
     * Méthode permettant récupérer le docteur sélectionné
     *
     * @return MyD : le docteur en question
     */
    public Doctors getD() {
        return MyD;
    }
}
