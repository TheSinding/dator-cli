package main.java.mediator;

import main.java.Components.ComputerComponent;
import main.java.Components.ComputerSystem;
import main.java.model.ConnectionModel;
import main.java.utils.Basket;
import main.java.utils.PrettyPrint;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import static main.java.Main.DB_PASS;
import static main.java.Main.DB_URL;
import static main.java.Main.DB_USER;

/**
 * Created by thesinding on 3/18/17.
 */
public class MainApp {
    private ConnectionModel dbConn;
    private Scanner s;
    private ArrayList<ComputerComponent> computerComponents;
    private ArrayList<ComputerSystem> computerSystems;
    private boolean connected;

    private Basket basket;

    public MainApp(){

        this.connected = this.connect(DB_URL, DB_USER, DB_PASS);
        this.computerComponents = this.listAllComponents();
        this.computerSystems = this.dbConn.getComputerSystem();
        basket = new Basket(this.dbConn);
    }

    public void launch(){
        if(!this.connected){
            System.out.println("ERROR: Cannot connect to db");
            System.exit(1);
        }
        PrettyPrint.printWelcome();

        s = new Scanner(System.in);
        Scanner choiceScanner;
        int id;
        while (true){
            System.out.println("\nWhat do you want to do?");
            System.out.printf("1: %4s \n", "List all Components");
            System.out.printf("2: %4s \n", "List all Computers");
            System.out.printf("3: %4s \n", "Price List");
            System.out.printf("4: %4s \n", "Price Offer");
            System.out.printf("5: %4s \n", "Sell");
            System.out.printf("6: %4s \n", "Restocking");
            System.out.printf("0: %4s \n", "Exit");
            System.out.printf("\n> ");

            switch (s.nextInt()){
                case 1:
                    PrettyPrint.printListComponents(computerComponents);
                    break;
                case 2:
                    PrettyPrint.printAComputer(this.dbConn.getComputerSystem(), computerComponents);
                    break;
                case 3:
                    PrettyPrint.printTotalPriceList(computerComponents);
                    break;
                case 4:
                    System.out.println("If you buy more than 1 computer system you get 2% off per computer system" +
                            "\nBut ONLY Computer Systems");
                    break;
                case 5:
                    this.sellComputers();
                    break;
                case 6:
                    boolean editing = true;
                    while (editing){
                        ArrayList<ComputerComponent> needsRestocking = PrettyPrint.printRestocking(computerComponents);
                        if(needsRestocking == null){
                            editing = false;
                            break;
                        }

                        System.out.println("\nWhich ID do you want to update?");
                        System.out.printf("0: %4s \n", "Exit");
                        System.out.printf("> ");
                        choiceScanner = new Scanner(System.in);
                        id = choiceScanner.nextInt();
                        switch (id){
                            case 0:
                                editing = false;
                                System.out.println("Aborting..");
                                break;
                            default:
                                boolean found = false;
                                for(ComputerComponent component : needsRestocking){
                                    if(component.getProductId() == id){
                                        found = true;
                                        choiceScanner = new Scanner(System.in);
                                        System.out.println("Current editing:");
                                        PrettyPrint.printRestockingRow(component);
                                        System.out.println("\nWhat is the new current stock?");
                                        System.out.printf("0: %4s \n", "Exit");
                                        System.out.printf("> ");
                                        id = choiceScanner.nextInt();
                                        component.setCurrentStock(id);
                                        try {
                                            this.dbConn.update(component.getClass().getSimpleName().toLowerCase(), component.getValuesMap());
                                            System.out.println("Success! Row has been updated!");
                                        } catch (SQLException e) {
                                            System.out.println("Error updating the row: " + e);
                                        }
                                        break;
                                    }
                                }
                                if(!found){
                                    System.out.println("A Product with ID of " + id + " does not exist or does not need restocking! \n Try again..");
                                }
                                break;
                        }
                    }



                    break;
                case 0:
                    System.exit(0);
                    break;
            }
        }

    }
    public boolean connect(String url, String user, String pass) {
        try {
            dbConn = new ConnectionModel(url, user, pass);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

    private ArrayList<ComputerComponent> listAllComponents(){
        return this.dbConn.getComponents();
    }

    public boolean addComponentToDB(ComputerComponent component){
        ResultSet r = this.dbConn.insert(component.getClass().getSimpleName().toLowerCase(), component.getValuesMap());
        return true;
    }



    public void sellComputers(){
        boolean selling;
        boolean computers = false;
        boolean choosen = false;
        int discount = -1;
        Scanner s;
        selling = true;
        System.out.println("1: Sell Computers");
        System.out.println("2: Sell Components");
        System.out.println("0: Exit");
        System.out.printf("> ");

        while(selling){
            s = new Scanner(System.in);
            int id = s.nextInt();
            switch (id){
                case 1:
                    computers = true;
                    choosen = true;
                    break;
                case 2:
                    choosen = true;
                    computers = false;
                    break;
                case 0:
                    selling = false;
                    System.out.println("Exiting sales..");
                break;
                default:
                    System.out.println("1: Sell Components");
                    System.out.println("2: Sell Computers");
                    System.out.println("0: Exit");
                    System.out.printf("> ");

                    break;
            }
            while (choosen && selling){
                System.out.println("Selling Components");
                if(computers){
                    PrettyPrint.printAComputer(computerSystems, computerComponents);
                } else {
                    PrettyPrint.printListComponents(computerComponents);
                }
                System.out.println("Enter an ID to sell:");
                System.out.printf("0: %4s \n", "Exit");
                System.out.printf("> ");
                s = new Scanner(System.in);
                id = s.nextInt();
                switch (id) {
                    case 0:
                        selling = false;
                        System.out.println("Exiting sales..");
                        break;
                    default:
                        boolean found = false;
                        if(computers){
                            for(ComputerSystem computerSystem : computerSystems){
                                if (computerSystem.getId() == id) {
                                    found = true;
                                    s = new Scanner(System.in);
                                    System.out.println("Current selling:");
                                    if(PrettyPrint.printAComputer(computerSystem, computerComponents) > 0){
                                        System.out.println("\nWhat is the amount?");
                                        System.out.printf("0: %4s \n", "Exit");
                                        System.out.printf("> ");
                                        int amount = s.nextInt();
                                        if(amount > 1){
                                            discount = amount;
                                        }
                                        this.basket.addToBasket(computerSystem, amount);
                                        break;
                                    } else {
                                        System.out.println("There isent enough in stock to sell this computer system! \n");
                                        break;
                                    }
                                }
                            }
                            if (!found) {
                                System.out.println("A Computer System with ID of " + id + " does not exist! \n Try again..");
                            }
                        } else {
                            for (ComputerComponent component : computerComponents) {
                                if (component.getProductId() == id) {
                                    found = true;
                                    s = new Scanner(System.in);
                                    System.out.println("Current selling:");
                                    PrettyPrint.printRestockingRow(component);
                                    System.out.println("\nWhat is the amount?");
                                    System.out.printf("0: %4s \n", "Exit");
                                    System.out.printf("> ");
                                    int amount = s.nextInt();
                                    this.basket.addToBasket(component, amount);
                                    break;
                                }
                            }
                            if (!found) {
                                System.out.println("A Product with ID of " + id + " does not exist! \n Try again..");
                            }
                        }
                    break;
                }
            }
        }
        System.out.printf("\n\n");
        if(basket.getComponentsInBasket().size() > 0){
            if(discount > 1 ){
                PrettyPrint.printTotalPriceList(basket.getComponentsInBasket(), discount);
            } else {
                PrettyPrint.printTotalPriceList(basket.getComponentsInBasket());
            }
        }
    }
}
