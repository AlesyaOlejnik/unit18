package com.company.xml.utils;

import com.company.entities.Devices;
import com.sun.tools.xjc.BadCommandLineException;
import com.sun.tools.xjc.Driver;
import com.sun.tools.xjc.XJCListener;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class XMLUtils {

    public static final String RESOURCES_DIR = "resources";

    public static boolean validateXml(String schemaFileName, String xmlFileName) {
        boolean result = false;
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(Path.of(RESOURCES_DIR, schemaFileName).toFile());
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(Files.newInputStream(Path.of(RESOURCES_DIR, xmlFileName))));
            result = true;
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Devices unmarshallXml(String xmlFileName) {
        try {
            JAXBContext context = JAXBContext.newInstance(Devices.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (Devices) unmarshaller.unmarshal(Path.of(RESOURCES_DIR, xmlFileName).toFile());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void marshallToXml(Devices jaxbZoo, String xmlFileName) {
        try {
            JAXBContext context = JAXBContext.newInstance(Devices.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(jaxbZoo, Path.of(RESOURCES_DIR, xmlFileName).toFile());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static boolean generateJAXBObjects(String directoryToGenerate, String schemaName) {
        boolean result = false;
        try {
            Driver.run(new String[]{"-d", directoryToGenerate, schemaName}, new XJCListener() {
                @Override
                public void error(SAXParseException e) {
                    e.printStackTrace();
                }

                @Override
                public void fatalError(SAXParseException e) {
                    e.printStackTrace();
                }

                @Override
                public void warning(SAXParseException e) {
                    e.printStackTrace();
                }

                @Override
                public void info(SAXParseException e) {
                    e.printStackTrace();
                }
            });
            result = true;
        } catch (BadCommandLineException e) {
            e.printStackTrace();
        }
        return result;
    }

    private XMLUtils() {
    }
}
