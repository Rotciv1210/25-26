package org.example;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;

public class SAXLibreria {
    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            // ✅ Cargar desde resources, como en Main.java
            InputStream is = SAXLibreria.class.getClassLoader().getResourceAsStream("libreria.xml");
            if (is == null) {
                throw new IllegalArgumentException("No se encontró libreria.xml en resources");
            }

            parser.parse(is, new LibrosHandler());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}