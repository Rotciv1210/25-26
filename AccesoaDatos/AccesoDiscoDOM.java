import java.io.File;
import java.io.IOException;

import javax.swing.event.DocumentEvent;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class AccesoDiscoDOM {
    
    public static void main(String[] args) {
        try {
            File inputFile = new File("discos.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            muestraDocumento(doc);

            modificarNodo(doc,"disco",1, "nuevo titulo");
            muestraDocumento(doc);
            guardarDocumento(doc,"discos2.xml");

            anadirNodo(doc,"1999","Disco nuevo","autor nuevo");
            muestraDocumento(doc);
            guardarDocumento(doc, "discos3.xml");



        } catch (ParserConfigurationException | IOException | org.xml.sax.SAXException e) {
            // TODO: handle exception
            e.printStackTrace();

        }
    }
public static void muestraDocumento(Document doc) {
    NodeList nList = doc.getElementsByTagName("disco");
    for (int temp = 0; temp < nList.getLength(); temp++) {
        Node nNode = nList.item(temp);
        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) nNode;

            System.out.println(eElement.getElementsByTagName("titulo").item(0).getTextContent());

            System.out.println("\tAutor: " + eElement.getElementsByTagName("artista").item(0).getTextContent());

            Node anioNode = eElement.getElementsByTagName("anio").item(0);
            if (anioNode != null) {
                System.out.println("\tFecha de publicacion: " + anioNode.getTextContent());
            } else {
                System.out.println("\tFecha de publicacion: (no disponible)");
            }

            Node cancionesNode = eElement.getElementsByTagName("canciones").item(0);
            if (cancionesNode != null) {
                System.out.println("\tcanciones: " + cancionesNode.getTextContent());
            }

            Node cancionNode = eElement.getElementsByTagName("cancion").item(0);
            if (cancionNode != null) {
                System.out.println("\tcancion: " + cancionNode.getTextContent());
            }
        }
    }
}


        public static void modificarNodo(Document doc, String elemento, int indice,String nuevoTitulo){
            NodeList nList = doc.getElementsByTagName(elemento);
            Node nodoIesimo = nList.item(indice);
            if(nodoIesimo.getNodeType() == Node.ELEMENT_NODE){

                Element elementoIesimo = (Element) nodoIesimo;
                elementoIesimo.getElementsByTagName("titulo").item(0).setTextContent("nuevo titulo");
            }
            System.out.println("\n\nModificar: Fichero XML actualizado correctamente");
        }

        public static void anadirNodo(Document doc, String publicacion, String titulo,String artista){

            Node ntitulo = doc.createElement("titulo");
            Node ntituloText = doc.createTextNode(titulo);
            ntitulo.appendChild(ntituloText);
            Node nautor = doc.createElement("artista");
            Node nautorText = doc.createTextNode(artista);
            nautor.appendChild(nautorText);

            Node ndisco = doc.createElement("disco");
            ((Element)ndisco).setAttribute("id", artista);

            ndisco.appendChild(ntitulo);
            ndisco.appendChild(nautor);

            Node raiz = doc.getChildNodes().item(0);
            raiz.appendChild(ndisco);

            System.out.println("\n\nAñadir: Fichero XMl actualizado correctamente");
        }

        private static void guardarDocumento(Document doc, String nombreFichero){

            try {
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source= new DOMSource(doc);
                StreamResult result = new StreamResult(new File(nombreFichero));
                transformer.transform(source, result);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }

    }
