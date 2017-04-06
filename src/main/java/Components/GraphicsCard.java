package main.java.Components;

import java.util.HashMap;

/**
 * Created by thesinding on 3/30/17.
 */
public class GraphicsCard extends ComputerComponent  {
    private String type;
    private double speed;

    public GraphicsCard(String name, int productId, double price) {
        super(name, productId, price);
    }
    public GraphicsCard(HashMap<String, String> values){
        super(values);
        this.type = values.get("type");
        this.speed = Double.parseDouble(values.get("speed"));
    }

    public GraphicsCard(String name, String type, double speed) {
        super(name);
        this.type = type;
        this.speed = speed;
    }

    public GraphicsCard(String type, double speed) {
        this.type = type;
        this.speed = speed;
    }

    public GraphicsCard(){

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
        values.put("speed", Double.toString(this.speed));
        values.put("type", "'"+ this.type + "'");
        values.put("productid", Integer.toString(this.getProductId()));
        return values;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
