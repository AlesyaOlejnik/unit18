package com.company.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "devices")
public class Devices {
    @XmlElement(name = "device",type = Device.class)
    private List<Device> devices;

    public Devices(List<Device> devices) {
        this.devices = devices;
    }

    public Devices() {
    }

    public List<Device> getDevices() {
        if (devices == null) {
            devices = new ArrayList<Device>();
        }
        return this.devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    @Override
    public String toString() {
        return "Devices{" +
                "devices=" + devices +
                '}';
    }
}
