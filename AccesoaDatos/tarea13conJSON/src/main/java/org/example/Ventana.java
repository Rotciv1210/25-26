package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana extends JFrame {

    private JLabel imagenRaza;
    private JButton btnAcceder;

    public Ventana(){

        setTitle("Pantalla 1");
        setSize(700,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new BorderLayout());


        imagenRaza = new JLabel();
        imagenRaza.setHorizontalAlignment(JLabel.CENTER);
        imagenRaza.setBorder(BorderFactory.createLineBorder(Color.black));

        btnAcceder = new JButton("Acceder");

        panel.add(imagenRaza, BorderLayout.CENTER);
        panel.add(btnAcceder, BorderLayout.SOUTH);

        add(panel, BorderLayout.NORTH);

        btnAcceder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        })
     }
}
