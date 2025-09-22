package org.example;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LibrosHandler extends DefaultHandler {

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if("libro".equals(qName)){
            System.out.println("Libro ID: "+attributes.getValue("id"));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String contenido = new String(ch,start,length).trim();
        if(!contenido.isEmpty()){
            System.out.println("Contenido: "+contenido);
        }
    }
}
