import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Ventana extends JFrame {

   private JComboBox<String> comboCategorias;
   private JList<String> listaTitulos;
   private JLabel imagenLabel;
   private JButton botonCargar;

   public Ventana(Map<String, List<Item>> categoriaMapa){

       setTitle("XML con SAX");
       setSize(700,400);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       setLocationRelativeTo(null);

       setLayout(new BorderLayout());

       JPanel panelIzquierdo = new JPanel(new BorderLayout());

       comboCategorias = new JComboBox<>(categoriaMapa.keySet().toArray(new String[0]));
       comboCategorias.setBorder(BorderFactory.createTitledBorder("Categoria"));

       listaTitulos = new JList<>();
       JScrollPane listaScroll = new JScrollPane(listaTitulos);
       listaScroll.setBorder(BorderFactory.createTitledBorder("Titulo"));

       panelIzquierdo.add(comboCategorias, BorderLayout.NORTH);
       panelIzquierdo.add(listaScroll,BorderLayout.CENTER);

       JPanel panelDerecho = new JPanel(new BorderLayout());

       imagenLabel = new JLabel();
       imagenLabel.setHorizontalAlignment(SwingConstants.CENTER);
       imagenLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

       botonCargar = new JButton("Cargar foto al azar");

       panelDerecho.add(imagenLabel, BorderLayout.CENTER);
       panelDerecho.add(botonCargar, BorderLayout.SOUTH);

       add(panelIzquierdo, BorderLayout.WEST);
       add(panelDerecho, BorderLayout.CENTER);

       comboCategorias.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               String categoria = (String)comboCategorias.getSelectedItem();
               List<Item> lista = categoriaMapa.get(categoria);
               if(lista != null){
                   listaTitulos.setListData(lista.stream().map(Item::getTitulo).toArray(String[]::new));
               }

           }
       });

       botonCargar.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               String categoria = (String)comboCategorias.getSelectedItem();
               List<Item> lista = categoriaMapa.get(categoria);
               if(lista != null && !lista.isEmpty()){
                   Random random = new Random();
                   Item imagenRandom = lista.get(random.nextInt(lista.size()));
                   try{
                       URL url = new URL(imagenRandom.getURLimagen());
                       BufferedImage img = ImageIO.read(url);
                       ImageIcon icono = new ImageIcon(img.getScaledInstance(300,300,Image.SCALE_SMOOTH));
                       imagenLabel.setIcon(icono);
                       imagenLabel.setText("");
                   } catch (Exception ex){
                       imagenLabel.setText("Error al cargar la imagen");
                   }
               }

           }
       });
       if(!categoriaMapa.isEmpty()){
           comboCategorias.setSelectedIndex(0);
       }
   }
}
