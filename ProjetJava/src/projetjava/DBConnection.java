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

    Connection conn;

    public DBConnection() {

    }

    public Connection getConnection() {
        String URL = null, password = null, user = null;
        try {

            File file = new File("DBLogIn.txt");
            Scanner inputFile = new Scanner(file);

            URL = inputFile.nextLine();
            password = inputFile.nextLine();
            user = inputFile.nextLine();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        try {
            conn = DriverManager.getConnection(URL, user, password);
            //inputFile.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
    
 
}
