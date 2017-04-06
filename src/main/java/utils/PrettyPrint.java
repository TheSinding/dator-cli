package main.java.utils;

import com.sun.org.apache.xpath.internal.SourceTree;
import main.java.Components.ComputerComponent;
import main.java.Components.ComputerSystem;

import java.awt.*;
import java.util.ArrayList;

import static main.java.utils.Names.*;

public class PrettyPrint {
    private static String spacer = "------------------------------";


    public static void printWelcome(){
        System.out.println("This is a DB project by Simon Sinding - 2017");
        System.out.println("Github.com/TheSinding");
        System.out.println("");
        System.out.println("|--------------------------------------------------|");
        System.out.println("| Disclaimer: I know this is a VERY messy codebase |");
        System.out.println("| But I had some nice GUI, which for some reason   |");
        System.out.println("| stopped working all of a sudden so I had to      |");
        System.out.println("| quickly put this togeather, that's why it's,     |");
        System.out.println("| really really really, shitty codebase.           |");
        System.out.println("|--------------------------------------------------|");
        System.out.println("");
        System.out.println("Welcome to Dator CLI (GUI wasn't working!)");
        System.out.println("Here is what you can do");


    }

    public static ArrayList<ComputerComponent> printRestocking(ArrayList<ComputerComponent> components){
        ArrayList<ComputerComponent> needsRestocking = new ArrayList<>();
        for(ComputerComponent component : components){
            if(component.getCurrentStock() <= component.getMinimumStock()){
                needsRestocking.add(component);
            }
        }
        if(!(needsRestocking.size() > 0)){
            System.out.println("Looks like nothing needs to be restocked! NOICE!");
            return null;
        }
        System.out.printf("|  %-30s  |  %-30s  |  %-30s  |  %-30s  |  %-30s  |\n", PRODUCTID.toString(), NAME.toString(),  PREFEREDSTOCK.toString(), CURRENTSTOCK.toString(), MINIMUMSTOCK.toString());
        System.out.printf("|  %-30s  |  %-30s  |  %-30s  |  %-30s  |  %-30s  |\n", spacer, spacer, spacer, spacer, spacer);
        for(ComputerComponent component : needsRestocking){
            if(component.getCurrentStock() <= component.getMinimumStock()){
                needsRestocking.add(component);
                 System.out.printf("|  %-30s  |  %-30s  |  %-30s  |  %-30s  |  %-30s  | %s \n"
                , component.getProductId()
                , component.getName()
                , Math.ceil(component.getPrice()) - 1
                , component.getCurrentStock()
                , component.getMinimumStock()
                 , (component.getCurrentStock() < component.getMinimumStock()) ? "* Needs restocking NOW" : "");
            }
        }
        return needsRestocking;
    }


    public static void printListComponents(ArrayList<ComputerComponent> components){
        System.out.printf("|  %-30s  |  %-30s  |  %-30s  |  %-30s  |\n",  PRODUCTID.toString(), NAME.toString(),  PRICE.toString(), CURRENTSTOCK.toString());
        System.out.printf("|  %-30s  |  %-30s  |  %-30s  |  %-30s  |\n", spacer, spacer, spacer, spacer);
        for(ComputerComponent component : components){
            System.out.printf("|  %-30s  |  %-30s  |  %-30s  |  %-30s  |\n", component.getProductId(), component.getName(), Math.ceil(component.getPrice()) - 1, component.getCurrentStock());
        }
    }

    public static void printAComputer(ArrayList<ComputerSystem> computers, ArrayList<ComputerComponent> components){
        System.out.printf("|  %-30s  |  %-30s  |  %-30s  |  %-30s  |  %-30s  |  %-30s  |  %-30s  |  %-30s  |  %-30s  |\n"
                , PRODUCTID.toString()
                , NAME.toString()
                , CPU.toString()
                , MOTHERBOARD.toString()
                , RAM.toString()
                , CASE.toString()
                , GRAPHICSCARD.toString()
                , PRICE.toString()
                , "Possible Build Amount");
        System.out.printf("|  %1$-30s  |  %1$-30s  |  %1$-30s  |  %1$-30s  |  %1$-30s  |  %1$-30s  |  %1$-30s  |  %1$-30s  |  %1$-30s  |\n", spacer);
        int i = 0;
        for(ComputerSystem computer : computers){
            double price = 0.0;
            int amount = 1000000000;
            boolean check = false;
            int id = computer.getId();
            String name = "", cpu= "", systemCase= "", ram= "", graphicscard= "", motherboard= "";
            for(ComputerComponent component : components){
                if(component.getProductId() == computer.getCpu()){
                    cpu = component.getName();
                    check = true;
                    price += Math.ceil(component.getPrice()) - 1;

                } else if(component.getProductId() == computer.getGraphicscard()){
                    graphicscard = component.getName();
                    check = true;
                    price += Math.ceil(component.getPrice()) - 1;

                } else if(component.getProductId() == computer.getMotherboard()){
                    motherboard = component.getName();
                    check = true;
                    price += Math.ceil(component.getPrice()) - 1;

                } else if(component.getProductId() == computer.getSystemcase()){
                    systemCase = component.getName();
                    check = true;
                    price += Math.ceil(component.getPrice()) - 1;

                } else if(component.getProductId() == computer.getRam()) {
                    ram = component.getName();
                    check = true;
                    price += Math.ceil(component.getPrice()) - 1;
                }
                if(check){
                    if(amount > component.getCurrentStock()){
                        amount = component.getCurrentStock();
                    }
                    check = false;
                }

            }

            name = computer.getName();
            System.out.printf("|  %-30s  |  %-30s  |  %-30s  |  %-30s  |  %-30s  |  %-30s  |  %-30s  |  %-30s  |  %-30s  |\n"
                    , id
                    , name
                    , cpu
                    , motherboard
                    , ram
                    , systemCase
                    , graphicscard
                    , price
                    , amount);

        }
    }

    public static int printAComputer(ComputerSystem computer, ArrayList<ComputerComponent> components){
        System.out.printf("|  %-30s  |  %-30s  |  %-30s  |  %-30s  |  %-30s  |  %-30s  |  %-30s  |  %-30s  |  %-30s  |\n"
                , PRODUCTID.toString()
                , NAME.toString()
                , CPU.toString()
                , MOTHERBOARD.toString()
                , RAM.toString()
                , CASE.toString()
                , GRAPHICSCARD.toString()
                , PRICE.toString()
                , "Possible Build Amount");
        System.out.printf("|  %1$-30s  |  %1$-30s  |  %1$-30s  |  %1$-30s  |  %1$-30s  |  %1$-30s  |  %1$-30s  |  %1$-30s  |  %1$-30s  |\n", spacer);
        int i = 0;
        double price = 0.0;
        boolean check = false;
        int amount = 1000000000;
        int id = computer.getId();
        String name = "", cpu= "", systemCase= "", ram= "", graphicscard= "", motherboard= "";
        for(ComputerComponent component : components){
            if(component.getProductId() == computer.getCpu()){
                cpu = component.getName();
                check = true;
                price += Math.ceil(component.getPrice()) - 1;

            } else if(component.getProductId() == computer.getGraphicscard()){
                graphicscard = component.getName();
                check = true;
                price += Math.ceil(component.getPrice()) - 1;

            } else if(component.getProductId() == computer.getMotherboard()){
                motherboard = component.getName();
                check = true;
                price += Math.ceil(component.getPrice()) - 1;

            } else if(component.getProductId() == computer.getSystemcase()){
                systemCase = component.getName();
                check = true;
                price += Math.ceil(component.getPrice()) - 1;

            } else if(component.getProductId() == computer.getRam()) {
                ram = component.getName();
                check = true;
                price += Math.ceil(component.getPrice()) - 1;
            }
            if(check){
                if(amount > component.getCurrentStock()){
                    amount = component.getCurrentStock();
                }
                check = false;
            }
        }
        name = computer.getName();
            System.out.printf("|  %-30s  |  %-30s  |  %-30s  |  %-30s  |  %-30s  |  %-30s  |  %-30s  |  %-30s  |  %-30s  |\n"
                    , id
                    , name
                    , cpu
                    , motherboard
                    , ram
                    , systemCase
                    , graphicscard
                    , price
                    ,amount);
    return amount;
    }



    public static void printTotalPriceList(ArrayList<ComputerComponent> components){
        double total = 0.0;
        System.out.printf("|  %-30s  |  %-30s  |  %-30s  |\n",  NAME.toString(),  PRICE.toString(), CATEGORY.toString());
        System.out.printf("|  %1$-30s  |  %1$-30s  |  %1$-30s  |\n", spacer);
        String category = "";
        for(ComputerComponent component : components){
            if(!category.equals(component.getKind())){
                category = component.getKind();
            }
            total += Math.ceil(component.getPrice()) - 1;
            System.out.printf("|  %-30s  |  %-30s  |  %-30s  |\n", component.getName(), Math.ceil(component.getPrice()) - 1, category);
        }

        double cal = total * 1.3;
        cal = Math.ceil(cal/100)*100 - 0.1;
        System.out.printf("|  %1$-30s  |  %1$-30s  |  %1$-30s  |\n", spacer);
        System.out.printf("\n");
        System.out.printf("|  %-30s  |  %-30s  |\n", "" ,  "Total");
        System.out.printf("|  %-30s  |  %-30.2f  |\n", "Without 30%" ,  total);
        System.out.printf("|  %-30s  |  %-30.2f  |\n", "With 30%" ,   cal);
    }

    public static void printTotalPriceList(ArrayList<ComputerComponent> components, int discount){
        double total = 0.0;
        String category = "";

        double discountAmount = discount * 0.02;
        System.out.printf("|  %-30s  |  %-30s  |\n",  NAME.toString(),  PRICE.toString());
        System.out.printf("|  %-30s  |  %-30s  |\n", spacer, spacer);
        for(ComputerComponent component : components){
            if(!category.equals(component.getKind())){
                category = component.getKind();
            }
            total += Math.ceil(component.getPrice()) - 1;
            System.out.printf("|  %-30s  |  %-30s  |\n", component.getName(), Math.ceil(component.getPrice()) - 1);
        }
        double cal = total * 1.3;
        cal = Math.ceil(cal/100)*100 - 0.1;
        double calWithDiscount = cal - ((cal * Math.min(discountAmount + 1.0, 1.20)) - cal);

        System.out.printf("|  %-30s  |  %-30s  |\n", spacer, spacer);
        System.out.printf("|  %-30s  |  %-30s  |\n", "" ,  "Total");
        System.out.printf("|  %-30s  |  %-30s  |\n", "Without 30%" ,  total);
        System.out.printf("|  %-30s  |  %-30.2f  |\n", "With 30%" ,  cal);
        System.out.printf("|  %-30s  |  %-30.2f  |\n", "With "+ discountAmount * 100 + "% discount" ,  calWithDiscount);
    }

    public static void printRestockingRow(ComputerComponent component){
        System.out.printf("|  %-30s  |  %-30s  |  %-30s  |  %-30s  |  %-30s  |\n", PRODUCTID.toString(), NAME.toString(),  PREFEREDSTOCK.toString(), CURRENTSTOCK.toString(), MINIMUMSTOCK.toString());
        System.out.printf("|  %-30s  |  %-30s  |  %-30s  |  %-30s  |  %-30s  |\n", spacer, spacer, spacer, spacer, spacer);
        System.out.printf("|  %-30s  |  %-30s  |  %-30s  |  %-30s  |  %-30s  |\n"
                , component.getProductId()
                , component.getName()
                , Math.ceil(component.getPrice()) - 1
                , component.getCurrentStock()
                , component.getMinimumStock()
               );
    }
}
