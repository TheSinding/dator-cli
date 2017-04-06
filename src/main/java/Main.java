package main.java;

import main.java.mediator.MainApp;
public class Main {
    public static boolean DEBUG = true;
    public static String DB_URL ="jdbc:postgresql://localhost:5432/computer";
    public static String DB_USER = "postgres";
    public static String DB_PASS = "root";


    public static void main(String[] args) {
        MainApp mainApp = new MainApp();
        mainApp.launch();
    }

}
