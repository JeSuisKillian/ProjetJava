/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Pierr
 */
public class Appointment {
     
    private Date date;
    private String time;
    private String clinic;
    private String patient;
    private String doctor;
    private String reason;
    private boolean available;
    
    public Appointment (Date d, String t ,String c, String p, String doc, String r, boolean a)
    {
       date=d;
       time=t;
       clinic=c;
       patient=p;
       doctor=doc;
       reason=r;
       available=a;
    }
    
    public Date getDate()
    {
        return date;
    }
    public String getTime()
    {
        return time;
    }
    public String getClinic()
    {
        return clinic;
    }
    public String getPatient()
    {
        return patient;
    }
    public String getDoctor()
    {
        return doctor;
    }
    public String getReason()
    {
        return reason;
    }
    public boolean getAivalable()
    {
        return available;
    }
    
    
}

