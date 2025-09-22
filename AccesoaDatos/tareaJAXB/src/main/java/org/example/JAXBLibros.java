// org/example/JAXBLibros.java

package org.example;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.InputStream;
import java.util.List;

public class JAXBLibros {
    public static void main(String[] args) throws JAXBException {
        InputStream is = JAXBLibros.class.getClassLoader().getResourceAsStream("libros.xml");
        if (is == null) {
            throw new IllegalArgumentException("No se encontró el archivo libros.xml en resources");
        }

        //  Usa Libreria.class (y opcionalmente Libros.class para mayor seguridad)
        JAXBContext context = JAXBContext.newInstance(Libreria.class, Libros.class);
        Unmarshaller um = context.createUnmarshaller();

        //  Deserializa como Libreria, no como Libros
        Libreria libreria = (Libreria) um.unmarshal(is);

        //  Recorre todos los libros
        List<Libros> libros = libreria.getLibros();
        for (Libros libro : libros) {
            System.out.println("ID: " + libro.getId());
            System.out.println("Título: " + libro.getTitulo());
            System.out.println("Autor: " + libro.getAutor());
            System.out.println("---------------------");
        }
    }
}