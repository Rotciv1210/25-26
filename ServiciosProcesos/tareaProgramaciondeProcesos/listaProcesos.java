package tareaProgramaciondeProcesos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class listaProcesos {
    
    public static void main(String[] args) {

        List<Procesos> lista = new ArrayList<>();

        BufferedReader lector;
        try {
            Process proceso = Runtime.getRuntime().exec("tasklist");
            lector = new BufferedReader(new InputStreamReader(proceso.getInputStream()));


            String linea;
            boolean inicioDatos = false;
            while ((linea = lector.readLine()) != null) {

                //System.out.println("DEBUG: "+linea);
                if(!inicioDatos){
                    if(linea.trim().startsWith("=")){
                        inicioDatos = true;
                    }
                    continue;
                }

                Procesos p = parsearLinea(linea);
                if(p != null){
                    lista.add(p);
                }
        }
            lector.close();
            Collections.sort(lista);

            for(Procesos p : lista){
                System.out.println(p);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static Procesos parsearLinea(String linea){
        try {
            String[] partes = linea.trim().split("\\s+");
            if (partes.length < 5) {
                return null;
            }

            String nombre = partes[0];
            int pid = Integer.parseInt(partes[1]);

            String memStr = partes[partes.length - 1]
                    .replace(".", "")
                    .replace(",", "")
                    .replace("K", "")
                    .trim();

            int memoria = Integer.parseInt(memStr);

            return new Procesos(nombre, memoria, pid);

        } catch (Exception e) {
            return null;
        }
    }


}
