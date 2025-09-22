package org.example;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "libro")
public class Libros {

    private int id;
    private String titulo;
    private String autor;
    private String isbn;
    private double precio;
    private int anio;

    @XmlAttribute
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @XmlElement
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @XmlElement
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }

    @XmlElement
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @XmlElement
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @XmlElement
    public int getAnio() {return anio;}
    public void setAnio(int anio) {this.anio = anio;}
}
