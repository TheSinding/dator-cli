package main.java.Components;

import java.util.HashMap;
import java.util.concurrent.atomic.DoubleAccumulator;


/**
 * Created by thesinding on 3/27/17.
 */
public abstract class ComputerComponent {
    private int productId;
    private String name;
    private String kind;


    private double price;
    private int currentStock;
    private int preferedStock;
    private int minimumStock;


    public ComputerComponent(int productId, String name, double price, int currentStock, int preferedStock, int minimumStock) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.currentStock = currentStock;
        this.preferedStock = preferedStock;
        this.minimumStock = minimumStock;
    }
    public ComputerComponent(HashMap<String, String> values){
        this.setCurrentStock(Integer.parseInt(values.get("currentstock")));
        this.setPreferedStock(Integer.parseInt(values.get("preferedstock")));
        this.setMinimumStock(Integer.parseInt(values.get("minimumstock")));
        this.setName(values.get("name"));
        this.setKind(values.get("kind"));
        this.setProductId(Integer.parseInt(values.get("productid")));
        this.setPrice(Double.parseDouble(values.get("price")));
    }

    public ComputerComponent(String name, int productId, double price) {
        this.name = name;
        this.productId = productId;
        this.price = price;
    }
    public ComputerComponent(String name) {
        this.name = name;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }

    public void setPreferedStock(int preferedStock) {
        this.preferedStock = preferedStock;
    }

    public void setMinimumStock(int minimumStock) {
        this.minimumStock = minimumStock;
    }

    public ComputerComponent() {

    }


    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getMinimumStock() {
        return minimumStock;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public int getPreferedStock() {
        return preferedStock;
    }

    public abstract HashMap<String, String> getValuesMap();


    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
}
