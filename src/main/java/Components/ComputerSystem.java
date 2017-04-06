package main.java.Components;

import java.util.HashMap;

/**
 * Created by Simon Sinding - Ren Remoulade on 4/5/17.
 */
public class ComputerSystem {
    private String name;
    private int id;
    private int cpu, systemcase, graphicscard, ram, motherboard;

    public ComputerSystem(String name, int cpu, int systemcase, int graphicscard, int ram, int motherboard) {
        this.name = name;
        this.cpu = cpu;
        this.systemcase = systemcase;
        this.graphicscard = graphicscard;
        this.ram = ram;
        this.motherboard = motherboard;
    }

    public ComputerSystem(HashMap<String, String> toComputerSystem) {
        this.id = Integer.parseInt(toComputerSystem.get("id"));
        this.name = toComputerSystem.get("name");
        this.cpu = Integer.parseInt(toComputerSystem.get("cpuid"));
        this.systemcase = Integer.parseInt(toComputerSystem.get("systemcaseid"));
        this.ram = Integer.parseInt(toComputerSystem.get("ramid"));
        this.motherboard = Integer.parseInt(toComputerSystem.get("mainboardid"));
        try {
            this.graphicscard = Integer.parseInt(toComputerSystem.get("graphicscardid"));
        } catch (NumberFormatException e ){
            this.graphicscard = -1;
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCpu() {
        return cpu;
    }

    public void setCpu(int cpu) {
        this.cpu = cpu;
    }

    public int getSystemcase() {
        return systemcase;
    }

    public void setSystemcase(int systemcase) {
        this.systemcase = systemcase;
    }

    public int getGraphicscard() {
        return graphicscard;
    }

    public void setGraphicscard(int graphicscard) {
        this.graphicscard = graphicscard;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getMotherboard() {
        return motherboard;
    }

    public void setMotherboard(int motherboard) {
        this.motherboard = motherboard;
    }

    public int getId() {
        return id;
    }
}
