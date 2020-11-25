/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava;
import java.sql.*;

/**
 *
 * @author Pierr
 */
public class InformationSearch {
    
    private Connection conn;
    private Patients MyP;
    private Doctors MyD;
    private Clinics MyC;
    
    public InformationSearch()
    {
        try {
            conn = new DBConnection().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
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

            }    

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
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
    public void chargeAllAppointmentP() {

        try {

            PreparedStatement st = conn.prepareStatement("SELECT*FROM Appointment WHERE Patient=?");
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
 public boolean testIDDoctor(String mail) {
        boolean usernameExists = false;


        try {
            
            PreparedStatement st = conn.prepareStatement("select mail from Doctors");
            ResultSet r1 = st.executeQuery();
            String usernameCounter;
            if (r1.next()) {
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

                MyD.setAppDoc(new Appointment(date, time,clinic, patient, doctor, reason, available));

            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
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
    
    public void chargeAllAppointmentC(String name)
    {
         

        try {
          
            PreparedStatement st= conn.prepareStatement("SELECT*FROM Appointment WHERE Clinic=?");
            st.setString(1, name);
            ResultSet r1 = st.executeQuery();
            
            while(r1.next())
            {
                
                java.util.Date date=r1.getDate(1);
                String time=r1.getString(7);
                String clinic=r1.getString(2);
                String doctor=r1.getString(3);
                String patient=r1.getString(4);
                String reason=r1.getString(5);
                boolean available=r1.getBoolean(6);
                
                MyC.setAppH(new Appointment(date, time, clinic, patient, doctor, reason, available));
                
            }
           
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    public Patients getP()
    {
        return MyP;
    }
    public Clinics getC()
    {
        return MyC;
    }
      public void fermeture()
    {
        try{
        conn.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}