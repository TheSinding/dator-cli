package main.java.utils;

import main.java.Components.ComputerComponent;
import main.java.Components.ComputerSystem;
import main.java.model.ConnectionModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Simon Sinding - Ren Remoulade on 4/5/17.
 */
public class Basket {
    private Scanner s;

    private ConnectionModel dbConn;
    private ArrayList<ComputerComponent> componentsInBasket = new ArrayList<>();
    private ArrayList<ComputerComponent>  computerComponentArrayList = new ArrayList<>();


    public Basket(ConnectionModel dbConn) {
        this.dbConn = dbConn;
        this.computerComponentArrayList = this.dbConn.getComponents();
    }

    private ArrayList<ComputerComponent> convertComputer(ComputerSystem computerSystem) {
        ArrayList<ComputerComponent> converted = new ArrayList<>();
            for (ComputerComponent component : this.computerComponentArrayList) {
                if (component.getProductId() == computerSystem.getCpu()) {
                    converted.add(component);

                } else if (component.getProductId() == computerSystem.getGraphicscard()) {
                    converted.add(component);

                } else if (component.getProductId() == computerSystem.getMotherboard()) {
                    converted.add(component);

                } else if (component.getProductId() == computerSystem.getSystemcase()) {
                    converted.add(component);

                } else if (component.getProductId() == computerSystem.getRam()) {
                    converted.add(component);
                }
            }

        return converted;
    }


    public void addToBasket(ComputerComponent component){
        this.updateList();
        componentsInBasket.add(component);
    }
    public void addToBasket(ComputerComponent component, int amount){
        this.updateList();
        for(int i = 0; i < amount; i++){
            componentsInBasket.add(component);
        }
    }
    public void addToBasket(ComputerSystem computerSystem){
        this.updateList();
        for(ComputerComponent component : convertComputer(computerSystem)){
            componentsInBasket.add(component);
        }
    }
    public void addToBasket(ComputerSystem computerSystem, int amount){
        this.updateList();
        for(int i = 0; i < amount; i++){
            for(ComputerComponent component : convertComputer(computerSystem)){
                componentsInBasket.add(component);
            }
        }
    }

    public double getComponentTotal(){
        this.updateList();
        double total = 0.0;
        for(ComputerComponent component : componentsInBasket){
            total += component.getPrice();
        }
        return total;
    }

    public ArrayList<ComputerComponent> getComponentsInBasket() {
        for(ComputerComponent component : componentsInBasket){
            this.sellComponent(component.getProductId());
        }
        System.out.printf("\n\n");
        return componentsInBasket;
    }

    private void updateList(){
        this.computerComponentArrayList = this.dbConn.getComponents();
    }

    public void sellComponent(int id){
        for(ComputerComponent component : computerComponentArrayList){
            if(component.getProductId() == id){
                if(component.getCurrentStock() == 0){
                    PrettyPrint.printRestockingRow(component);
                    System.out.println("There is no more in stock");
                    break;
                }
                int oldStock = component.getCurrentStock();
                int newStock = component.getCurrentStock() - 1;
                if(newStock < 0){
                    newStock = 0;
                }
                component.setCurrentStock(newStock);
                PrettyPrint.printRestockingRow(component);
                try {
//                    System.out.println(component.getValuesMap());
                    this.dbConn.update(component.getClass().getSimpleName().toLowerCase(), component.getValuesMap());
                } catch (SQLException e) {
                    component.setCurrentStock(oldStock);
                    System.out.println("Error: Could not sell the component  - " + e);
                }
            }
        }
    }

}
