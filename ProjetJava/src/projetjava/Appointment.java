/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava;

import java.util.GregorianCalendar;

/**
 *
 * @author Pierr
 */
public class Appointment {
    
    private GregorianCalendar date;
    private Patients patient;
    private Doctors doctor;
    private String motif;
    private boolean disponible;
    
    public Appointment (GregorianCalendar d, Doctors doc, String m, boolean dispo)
    {
        date = d;
        doctor=doc;
        motif = m;
        disponible = dispo;
    }
}
