package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ventana extends JFrame {

    private JLabel imagenLabel;
    private JLabel nombreLabel;

    public Ventana() {

        setTitle("Pantalla 1");
        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        imagenLabel = new JLabel("", JLabel.CENTER);
        add(imagenLabel, BorderLayout.CENTER);

        nombreLabel = new JLabel("", JLabel.CENTER);
        nombreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(nombreLabel, BorderLayout.NORTH);

        JButton btnAcceder = new JButton("Acceder");
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.add(btnAcceder);
        add(panelBoton, BorderLayout.SOUTH);

        btnAcceder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PantallaRazas();
            }
        });

        cargarRazaAleatoria();

        setVisible(true);
    }

    private void cargarRazaAleatoria() {

        try {
            String json = leerURL("https://dog.ceo/api/breeds/image/random");
            JSONObject obj = new JSONObject(json);
            String urlImagen = obj.getString("message");

            String[] partes = urlImagen.split("/");
            String raza = partes[partes.length - 2];

            nombreLabel.setText("Raza: " + raza);

            ImageIcon icon = new ImageIcon(new URL(urlImagen));
            Image img = icon.getImage().getScaledInstance(300, 250, Image.SCALE_SMOOTH);
            imagenLabel.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            nombreLabel.setText("Error");
            e.printStackTrace();
        }
    }

    private static String leerURL(String urlString) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(urlString).openStream()));
        StringBuilder buffer = new StringBuilder();
        int read;
        char[] chars = new char[1024];
        while ((read = reader.read(chars)) != -1) {
            buffer.append(chars, 0, read);
        }

        reader.close();

        return buffer.toString();

    }


    class PantallaRazas extends JFrame {
        private JList<String> listaRazas;
        private JLabel imagenLabel;
        private JLabel nombreLabel;
        private JTextArea descripcionArea;

        public PantallaRazas() {
            setTitle("Pantalla 2 - Lista de Razas");
            setSize(700, 400);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());

            // Panel izquierdo (lista de razas)
            listaRazas = new JList<>();
            JScrollPane scroll = new JScrollPane(listaRazas);
            scroll.setPreferredSize(new Dimension(200, 0));
            add(scroll, BorderLayout.WEST);

            // Panel derecho
            JPanel panelDerecho = new JPanel(new BorderLayout());
            imagenLabel = new JLabel("", JLabel.CENTER);
            nombreLabel = new JLabel("Seleccione una raza", JLabel.CENTER);
            nombreLabel.setFont(new Font("Arial", Font.BOLD, 14));
            descripcionArea = new JTextArea();
            descripcionArea.setEditable(false);

            panelDerecho.add(imagenLabel, BorderLayout.NORTH);
            panelDerecho.add(nombreLabel, BorderLayout.CENTER);
            panelDerecho.add(new JScrollPane(descripcionArea), BorderLayout.SOUTH);

            add(panelDerecho, BorderLayout.CENTER);

            cargarListaRazas();

            listaRazas.addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting()) {
                    String raza = listaRazas.getSelectedValue();
                    if (raza != null) {
                        mostrarRaza(raza);
                    }
                }
            });

            setVisible(true);
        }

        private void cargarListaRazas() {

            try {
                String json = leerURL("https://dog.ceo/api/breeds/list/all");
                JSONObject obj = new JSONObject(json).getJSONObject("message");
                List<String> razas = new ArrayList<>();

                for (String key : obj.keySet()) {
                    razas.add(key);
                }
                Collections.sort(razas);
                listaRazas.setListData(razas.toArray(new String[0]));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void mostrarRaza(String raza) {
            try {
                String json = leerUrl("https://dog.ceo/api/breed/" + raza + "/images/random");
                JSONObject obj = new JSONObject(json);
                String urlImagen = obj.getString("message");

                nombreLabel.setText("Raza: " + raza);

                ImageIcon icon = new ImageIcon(new URL(urlImagen));
                Image img = icon.getImage().getScaledInstance(250, 200, Image.SCALE_SMOOTH);
                imagenLabel.setIcon(new ImageIcon(img));

                descripcionArea.setText("Subrazas:\n");

                // Buscar subrazas
                String json2 = leerUrl("https://dog.ceo/api/breeds/list/all");
                JSONObject allBreeds = new JSONObject(json2).getJSONObject("message");
                JSONArray subrazas = allBreeds.getJSONArray(raza);

                if (subrazas.length() == 0) {
                    descripcionArea.append("No hay subrazas disponibles.");
                } else {
                    for (int i = 0; i < subrazas.length(); i++) {
                        descripcionArea.append("- " + subrazas.getString(i) + "\n");
                    }
                }
            } catch (Exception e) {
                nombreLabel.setText("Error mostrando raza");
                e.printStackTrace();
            }
        }

        private static String leerUrl(String urlString) throws Exception {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(urlString).openStream()));
            StringBuilder buffer = new StringBuilder();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);
            reader.close();
            return buffer.toString();
        }
    }
}