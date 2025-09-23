import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Aleatorios {

    public static void main(String[] args) {
        BufferedReader lectorConsola = new BufferedReader(new InputStreamReader(System.in));
        String linea;

        System.out.println("Introduce líneas (escribe 'fin' para terminar):");

        try {
            while ((linea = lectorConsola.readLine()) != null) {
                if ("fin".equalsIgnoreCase(linea.trim())) {
                    System.out.println("Hasta la vista");
                    break;
                }

                // Lanzamos el proceso hijo: ejecutamos "java HijoAleatorio"
                ProcessBuilder pb = new ProcessBuilder("java", "HijoAleatorio");
                Process proceso = pb.start();

                // Leemos la salida del hijo
                BufferedReader salidaHijo = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
                String numeroStr = salidaHijo.readLine();

                // Esperamos a que el proceso hijo termine
                int exitCode = proceso.waitFor();

                if (exitCode == 0 && numeroStr != null) {
                    System.out.println(numeroStr); // Imprimimos el número recibido
                } else {
                    System.err.println("Error al obtener número aleatorio del hijo.");
                }
            }
        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("Proceso interrumpido: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}