/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava;
import java.util.GregorianCalendar;

/**
 *
 * @author tombe
 */
public class Rdv {
    private GregorianCalendar date;
    private Patients patient;
    //private Doctors doctor;
    private String motif;
    private boolean disponible;
    
    public Rdv(GregorianCalendar d, Patients p, String m, boolean dispo)
    {
        date = d;
        patient = p;
        motif = m;
        disponible = dispo;
    }
    
    
}
