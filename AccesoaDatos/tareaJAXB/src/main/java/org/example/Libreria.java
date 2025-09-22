// org/example/Libreria.java

package org.example;

import jakarta.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "libreria")
@XmlAccessorType(XmlAccessType.FIELD)
public class Libreria {

    @XmlElement(name = "libro")
    private List<Libros> libros; // ¡Sigue usando tu clase actual "Libros"!

    public Libreria() {}

    public List<Libros> getLibros() {
        return libros;
    }

    public void setLibros(List<Libros> libros) {
        this.libros = libros;
    }
}