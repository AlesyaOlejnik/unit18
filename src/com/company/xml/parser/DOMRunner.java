package com.company.xml.parser;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Path;

public class DOMRunner {

    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(Path.of("resources", "devices.xml").toFile());
            Element device = document.getDocumentElement();
            printDocument(device);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }

    private static void printDocument(Element zoo) {
        NodeList childNodes = zoo.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            switch (node.getNodeType()) {
                case Node.ELEMENT_NODE:
                    System.out.println("Tag name: " + node.getLocalName());
                    printAttributes(node);
                    printDocument((Element) node);
                    break;
                case Node.TEXT_NODE:
                    String textContent = node.getTextContent();
                    if (textContent.trim().length() != 0) {
                        System.out.println("Tag value: " + textContent);
                    }
                    break;
            }
        }
    }

    private static void printAttributes(Node node) {
        NamedNodeMap attributes = node.getAttributes();
        for (int j = 0; j < attributes.getLength(); j++) {
            Node attribute = attributes.item(j);
            System.out.println("Attribute name: " + attribute.getLocalName() + " attribute value: " + attribute.getNodeValue());
        }
    }
}
