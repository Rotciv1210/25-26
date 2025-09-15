import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {

    public Ventana(){

        setTitle("XML con SAX");
        setSize(700,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel panelIzquierdo =  new JPanel(new BorderLayout());

        JComboBox<String> comboCategorias = new JComboBox<>(new String[]{"Item 1","Item 2","Item 3","Item 4","Item 5"});
        comboCategorias.setForeground(Color.RED);
        comboCategorias.setFont(new Font("Arial",Font.BOLD,14));
        comboCategorias.setBorder(BorderFactory.createTitledBorder("Categoria"));

        JList<String> listaTitulos = new JList<>(new String[]{"Item 1","Item 2","Item 3","Item 4","Item 5"});
        listaTitulos.setFont(new Font("Arial",Font.BOLD,16));
        listaTitulos.setForeground(Color.RED);
        JScrollPane scrollLista = new  JScrollPane(listaTitulos);
        scrollLista.setBorder(BorderFactory.createTitledBorder("Titulo"));

        panelIzquierdo.add(comboCategorias,BorderLayout.NORTH);
        panelIzquierdo.add(scrollLista,BorderLayout.CENTER);

        JPanel panelDerecho = new JPanel(new BorderLayout());

        JLabel imagenLabel = new JLabel();
        imagenLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imagenLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JButton botonCargar = new JButton("Cargar foto al azar");

        panelDerecho.add(imagenLabel,BorderLayout.CENTER);
        panelDerecho.add(botonCargar,BorderLayout.SOUTH);

        add(panelIzquierdo, BorderLayout.WEST);
        add(panelDerecho, BorderLayout.CENTER);

    }
}
