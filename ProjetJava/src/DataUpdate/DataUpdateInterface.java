/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataUpdate;

/**
 *
 * @author Pierr
 */
public interface DataUpdateInterface {

    public void addPatients(String surName, String firstName, String age, String gender,
            String mail, String password, String adress);

    public void chooseAppointment(String doc, String time, String Clinic, String reason, java.util.Date date);

    public void addDoctors(String surName, String firstName, String mail,
            String pass, String specialisation, String qualification, String investment, String clinic, String clinic2);

    public void DeletePatient(String mail);

    public void DeleteDoctor(String mail);

    public void updatePatient(String surName, String firstName, String age, String adress,
            String gender, String mail);
}
