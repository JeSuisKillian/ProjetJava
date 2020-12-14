/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InformationSearch;

import java.util.ArrayList;

/**
 *
 * @author Pierr
 */
public interface InformationSearchInterface {

    public boolean testIDPatient(String mail);

    public void setPatient(String mail);

    public boolean testPassMail(String mail, String pass);

    public void chargeAllAppointmentP();

    public boolean testIDDoctor(String mail);

    public void setDoctors(String mail);

    public boolean testDocPassMail(String mail, String pass);

    public void chargeAllDocAppointment();

    public void setClinic();

    public void chargeAllAppointmentC(String name);

    public ArrayList getInfoP();

    public ArrayList NameDoctor();

    public boolean checkDocH(String doc, String clinic);

    public String returnMailDoc(String name);

    public String returnNameFullP(String mail);

    public ArrayList returnSurnameP();

    public String returnPatientMail(String name);

    public ArrayList AdvancedResearch(String reason);
}
