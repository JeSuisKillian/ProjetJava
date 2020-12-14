/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reporting;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import org.jfree.chart.*;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Pierr
 */
public class Reporting {
    
    private Connection conn;
    
    public Reporting(Connection c)
    {
        conn=c;
    }
    public void PieGraph()
    {
        try {
        
        PreparedStatement st = conn.prepareStatement("select DISTINCT Clinic from Appointment");
        ResultSet rs = st.executeQuery();
        ArrayList<String> temp = new ArrayList<>();
        ArrayList<Integer> temp2 = new ArrayList<>();
        DefaultPieDataset dataset = new DefaultPieDataset();
        
        while (rs.next())
        {
            temp.add(rs.getString("Clinic"));
        }
      for (int i=0; i<temp.size();++i)
      {
          PreparedStatement st1 = conn.prepareStatement("select count(*) from Appointment where Clinic = ?");
          st1.setString(1, temp.get(i));
          ResultSet rs1 = st1.executeQuery();
          while(rs1.next())
          {
              temp2.add(rs1.getInt(1));
          }
          
      }
      
      for(int j=0; j<temp2.size();++j)
      {
          dataset.setValue(temp.get(j), temp2.get(j));
      }
       JFreeChart chart = ChartFactory.createPieChart("Number of appointments by clinic", dataset, true, true, Locale.FRENCH);
        
        int x = 235;
        int y = 250;
        
        File f = new File("graphique125.png");
            try {
                ChartUtilities.saveChartAsPNG(f, chart, x,y);
            } catch (IOException ex) {
               ex.printStackTrace();
            }
        
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    
}
