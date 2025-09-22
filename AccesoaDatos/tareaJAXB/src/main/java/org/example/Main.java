package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        try{

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document doc = builder.parse(
                    Main.class.getClassLoader().getResourceAsStream("libreria.xml")
            );

            NodeList libros =  doc.getElementsByTagName("libro");

            for (int i = 0; i < libros.getLength(); i++){
                Element libro = (Element) libros.item(i);

                String id = libro.getAttribute("id");
                String titulo = libro.getElementsByTagName("titulo").item(0).getTextContent();
                String autor = libro.getElementsByTagName("autor").item(0).getTextContent();
                String isbn = libro.getElementsByTagName("isbn").item(0).getTextContent();
                String precio = libro.getElementsByTagName("precio").item(0).getTextContent();
                String anio  = libro.getElementsByTagName("anio").item(0).getTextContent();

                System.out.println("Libro ID: "+id);
                System.out.println("Titulo: "+titulo);
                System.out.println("Autor: "+autor);
                System.out.println("ISBN: "+isbn);
                System.out.println("Precio: "+precio);
                System.out.println("Anio: "+anio);
                System.out.println("---------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}