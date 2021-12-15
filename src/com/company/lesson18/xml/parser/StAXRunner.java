package com.company.lesson18.xml.parser;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class StAXRunner {

    public static void main(String[] args) {
        XMLInputFactory factory = XMLInputFactory.newFactory();
        try {
            XMLStreamReader streamReader = factory.createXMLStreamReader(Files.newInputStream(Path.of("resources", "new_devices.xml")));
            while (streamReader.hasNext()) {
                int event = streamReader.next();
                switch (event) {
                    case XMLStreamReader.START_ELEMENT:
                        String localName = streamReader.getLocalName();
                        System.out.println("Tag started: " + localName);
                        switch (localName) {

                        }
                        if (streamReader.hasText()) {
                            String elementText = streamReader.getElementText();
                            System.out.println("Tag value: " + elementText);
                        }
                        for (int i = 0; i < streamReader.getAttributeCount(); i++) {
                            System.out.println("Attribute name: " + streamReader.getAttributeLocalName(i) + " Attribute value: " + streamReader.getAttributeValue(i));
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        break;
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
