package main.java.Components;

import java.util.HashMap;

/**
 * Created by thesinding on 3/30/17.
 */
public class Ram extends ComputerComponent  {
    private double busSpeed;
    private String type;

    public Ram() {
    }

    public Ram(String name, int productId, double price) {
        super(name, productId, price);
    }
    public Ram(HashMap<String, String> values){
        super(values);
        this.busSpeed = Double.parseDouble(values.get("busspeed"));
        this.type = values.get("type");
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
        values.put("type", "'"+ this.type + "'");
        values.put("busspeed", Double.toString(this.busSpeed));
        values.put("productid", Integer.toString(this.getProductId()));
        return values;
    }

    public double getBusSpeed() {
        return busSpeed;
    }

    public void setBusSpeed(double busSpeed) {
        this.busSpeed = busSpeed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
