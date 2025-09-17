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
            File inputFile = new File("image-of-the-day.rss");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            List<Item> items = new ArrayList<>();
            DefaultHandler handler = new DefaultHandler(){
                Item itemEscogido = null;
                StringBuilder contenido = new StringBuilder();
                boolean esteItem = false;

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if(qName.equalsIgnoreCase("item")){
                        esteItem = true;
                        itemEscogido = new Item();
                    } else if (qName.equalsIgnoreCase("enclosure")) {
                        if(itemEscogido != null){
                            itemEscogido.setURLimagen(attributes.getValue("url"));
                        }
                    }
                    contenido.setLength(0);
                }

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    contenido.append(ch,start,length);
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if(esteItem){
                        switch (qName.toLowerCase()){
                            case "titulo":
                                itemEscogido.setTitulo(contenido.toString().trim());
                                break;
                            case "categoria":
                                itemEscogido.setCategoria(contenido.toString().trim());
                                break;
                            case "item":
                                if(itemEscogido.getTitulo() != null && itemEscogido.getURLimagen() != null){
                                    items.add(itemEscogido);
                                }
                                esteItem = false;
                                break;
                        }
                    }
                }
            };

            saxParser.parse(inputFile, handler);
            Map<String, List<Item>> mapaCategoria = new HashMap<>();
            for(Item i : items){
                String cate = (i.getCategoria()== null || i.getCategoria().isEmpty()) ? "Sin categoria" : i.getCategoria();
                mapaCategoria.computeIfAbsent(cate, k -> new ArrayList<>()).add(i);
            }

            SwingUtilities.invokeLater(()-> new Ventana(mapaCategoria).setVisible(true));

        } catch (Exception e){
            e.printStackTrace();
        }
        }
    }