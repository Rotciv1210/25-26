import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.swing.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        try{

            //se crea el objeto file con la ruta del xml a analizar
            File inputFile = new File("image-of-the-day.rss");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setNamespaceAware(true);
            SAXParser saxParser = factory.newSAXParser();

            //Se crea la lista donde se van a almacenar los items
            List<Item> items = new ArrayList<>();
            //se crea el handler para el SAX
            DefaultHandler handler = new DefaultHandler(){
                Item itemEscogido = null;
                StringBuilder contenido = new StringBuilder();
                boolean esteItem = false;//booleano para saber si estamos dentro del item

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    //Si se encuentra un elemento item se crea un Objeto item
                    if (qName.equalsIgnoreCase("item")) {
                        esteItem = true;
                        itemEscogido = new Item();
                        //Si se encuentra un elemento thumnail dentro del xml
                    } else if (localName.equalsIgnoreCase("thumbnail") || qName.equalsIgnoreCase("thumbnail")) {
                        if (itemEscogido != null) {
                            //se extrae la url de la imagen del item
                            String url = attributes.getValue("url");
                            if (url != null && !url.trim().isEmpty()) {
                                itemEscogido.setURLimagen(url.trim());
                                System.out.println("URL de la imagen" + url.trim());
                            } else {
                                System.out.println("No se encuentra la URL de la imagen");
                            }
                        }
                    }
                    //Se reinicia el StringBuilder para añadir contenido nuevo
                    contenido.setLength(0);
                }

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    //Se guarda el contenido de dentro de los elementos
                    contenido.append(ch,start,length);
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    //el switch entra solo cuando encuentra un elemento item
                    if(esteItem){
                        switch (qName.toLowerCase()){
                            case "title":
                                //Para saber si ha guardado bien el elemento item se hace un sout de comprobacion
                                System.out.println("Titulo encontrado: " + contenido.toString().trim());
                                if(itemEscogido!= null && itemEscogido.getTitulo() == null){
                                    itemEscogido.setTitulo(contenido.toString().trim());
                                }
                                break;
                                //se guarda la categoria del item
                            case "category":
                                itemEscogido.setCategoria(contenido.toString().trim());
                                break;
                            case "item":
                                //se agrega un item a la lista si tiene titulo y el url de la imagen es correcto
                                if(itemEscogido.getTitulo() != null && itemEscogido.getURLimagen() != null){
                                    items.add(itemEscogido);
                                    System.out.println("Se ha agregado el siguiente item" + itemEscogido.getTitulo() + " | URL: " + itemEscogido.getURLimagen());
                                } else { // se descarta el item si el titulo o la url es incorrecta
                                    System.out.println("Se ha descartado el siguiente item" + itemEscogido.getTitulo() + ", imagen=" + itemEscogido.getURLimagen());
                                }
                                esteItem = false; // se sale del item
                                break;
                        }
                    }
                }
            };

            //Parsea el XML
            saxParser.parse(inputFile, handler);
            //se crea el mapa para agrupar los items por categoria
            Map<String, List<Item>> mapaCategoria = new HashMap<>();
            for(Item i : items){
                //Se asigna "Sin categoria" si la categoria del item es nula o no tiene
                String cate = (i.getCategoria()== null || i.getCategoria().isEmpty()) ? "Sin categoria" : i.getCategoria();
                // Verificar si la categoría ya existe en el mapa
                if (!mapaCategoria.containsKey(cate)) {
                    // Si no existe, crear una nueva lista vacía
                    mapaCategoria.put(cate, new ArrayList<>());
                }
                // Obtener la lista de esa categoría y añadir el item
                mapaCategoria.get(cate).add(i);            }

            //Se crea y se muestra la ventana
            SwingUtilities.invokeLater(()-> new Ventana(mapaCategoria).setVisible(true));

        } catch (Exception e){
            //Manejo de excepciones
            e.printStackTrace();
        }
        }
    }