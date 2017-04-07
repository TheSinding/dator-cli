package main.java;

import main.java.mediator.MainApp;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static boolean DEBUG = true;
    public static String DB_URL ="jdbc:postgresql://localhost:5432/sisin16_computer";
    public static String DB_USER = "postgres";
    public static String DB_PASS = "root";


    public static void main(String[] args) {
        while (true){
            boolean entered = false;
            while(!entered){
                System.out.println("Enter user for your Postgres db");
                System.out.printf("> ");
                Scanner s = new Scanner(System.in);
                DB_USER = s.next();
                System.out.println("Enter password for your Postgres db");
                System.out.printf("> ");
                s = new Scanner(System.in);
                DB_PASS = s.next();
                entered = true;
            }
            try{
                MainApp mainApp = new MainApp();
                mainApp.launch();
            } catch (SQLException e){
                System.out.println("Error " + e);
                entered = false;
            }
        }
    }

}
