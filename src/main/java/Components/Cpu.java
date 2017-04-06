package main.java.Components;

import javafx.beans.property.StringProperty;

import java.util.HashMap;

/**
 * Created by thesinding on 3/27/17.
 */
public class Cpu extends ComputerComponent {
    private double busSpeed;
    private String socket;

    public Cpu(HashMap<String, String> values){
        super(values);
        this.setBusSpeed(Double.parseDouble(values.get("busspeed")));
        this.setSocket(values.get("socket"));
    }

    public void setBusSpeed(double busSpeed) {
        this.busSpeed = busSpeed;
    }
    public void setSocket(String socket) {
        this.socket = socket;
    }

    public double getBusSpeed() {

        return busSpeed;
    }

    @Override
    public HashMap<String, String> getValuesMap() {
        HashMap<String, String> values = new HashMap<>();

        values.put("currentStock",   Integer.toString(this.getCurrentStock()));
        values.put("minimumstock",   Integer.toString(this.getMinimumStock()));
        values.put("preferedstock",  Integer.toString(this.getPreferedStock()));
        values.put("kind",           "'"+ this.getClass().getSimpleName().toLowerCase() +"'");
        values.put("name",           "'"+ this.getName() +"'");
        values.put("price",          Double.toString(this.getPrice()));
        values.put("busspeed", Double.toString(this.busSpeed));
        values.put("socket", "'"+ this.socket + "'");
        values.put("productid", Integer.toString(this.getProductId()));
        return values;
    }

    public String getSocket() {
        return socket;
    }


}
