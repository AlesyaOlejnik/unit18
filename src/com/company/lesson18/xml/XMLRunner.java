package com.company.lesson18.xml;

import com.company.lesson18.entities.Devices;
import com.company.lesson18.xml.utils.XMLUtils;

public class XMLRunner {
    public static void main(String[] args) {
        boolean result = XMLUtils.validateXml("devices.xsd", "devices.xml");
        System.out.println(result);

        Devices devices = XMLUtils.unmarshallXml("devices.xml");
        System.out.println(devices);

        XMLUtils.marshallToXml(devices, "new_devices.xml");


    }

}
