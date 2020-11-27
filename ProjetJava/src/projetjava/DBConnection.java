/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava;

import java.sql.*;
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Pierr
 */
public class DBConnection {

    public DBConnection() {

    }

    public Connection getConnection() throws Exception {
        String URL = null, password= null, user= null;
        try {
            File file = new File("DBLogIn.txt");
            Scanner inputFile = new Scanner(file);

            URL = inputFile.nextLine();
            password = inputFile.nextLine();
            user = inputFile.nextLine();            

            //String URL = "jdbc:mysql://mysql-pierre-alexandre.alwaysdata.net:3306/pierre-alexandre_caresystem";
            //String password = "Amoxcilline98";
            //String user = "219005";
            inputFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        return DriverManager.getConnection(URL, user, password);
    }
}
