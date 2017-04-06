package main.java.Components;

import java.util.HashMap;

/**
 * Created by thesinding on 3/30/17.
 */
public class SystemCase extends ComputerComponent  {
    private String formfactor;

    public SystemCase(){
        super();
    }

    public SystemCase(HashMap<String, String> values){
        super(values);
        this.formfactor = values.get("type");
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
        values.put("type", "'"+ this.formfactor + "'");
        values.put("productid", Integer.toString(this.getProductId()));
        return values;
    }

    public SystemCase(String name, int productId, double price){
        super(name, productId, price);
    }

    public String getFormfactor() {
        return formfactor;
    }

    public void setFormfactor(String formfactor) {
        this.formfactor = formfactor;
    }
}
