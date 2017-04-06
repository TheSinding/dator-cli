package main.java.Components;

import java.util.HashMap;

/**
 * Created by thesinding on 3/30/17.
 */
public class Motherboard extends ComputerComponent {
    private boolean onboardGraphics;
    private String socket;
    private String formfactor;
    private String type;


    public Motherboard(String name, int productId, double price) {
        super(name, productId, price);
    }
    public Motherboard(HashMap<String, String> values){
        super(values);
        this.formfactor = values.get("formfactor");
        this.type = values.get("type");
        this.socket = values.get("socket");
        this.onboardGraphics = Boolean.parseBoolean(values.get("onboardgraphics"));
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
        values.put("socket", "'"+ this.socket + "'");
        values.put("formfactor", "'"+ this.formfactor + "'");
        values.put("type", "'"+ this.type + "'");
        values.put("onboardgraphics", String.valueOf(this.onboardGraphics));
        values.put("productid", Integer.toString(this.getProductId()));
        return values;
    }

    public void setOnboardGraphics(boolean onboardGraphics) {
        this.onboardGraphics = onboardGraphics;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public void setFormfactor(String formfactor) {
        this.formfactor = formfactor;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isOnboardGraphics() {

        return onboardGraphics;
    }

    public String getSocket() {
        return socket;
    }

    public String getFormfactor() {
        return formfactor;
    }

    public String getType() {
        return type;
    }
}
