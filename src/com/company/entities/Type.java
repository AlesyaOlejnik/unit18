package com.company.entities;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "type")
public class Type {
    @XmlElement(name = "peripheral")
    private boolean peripheral;
    @XmlElement(name = "energy")
    private String energy;
    @XmlElement(name = "cooler")
    private boolean cooler;

    public Type(boolean peripheral, String energy, boolean cooler) {
        this.peripheral = peripheral;
        this.energy = energy;
        this.cooler = cooler;
    }

    public Type() {
    }

    public boolean isPeripheral() {
        return peripheral;
    }

    public void setPeripheral(boolean peripheral) {
        this.peripheral = peripheral;
    }

    public String getEnergy() {
        return energy;
    }

    public void setEnergy(String energy) {
        this.energy = energy;
    }

    public boolean isCooler() {
        return cooler;
    }

    public void setCooler(boolean cooler) {
        this.cooler = cooler;
    }

    @Override
    public String toString() {
        return "Type{" +
                "peripheral=" + peripheral +
                ", energy='" + energy + '\'' +
                ", cooler=" + cooler +
                '}';
    }
}
