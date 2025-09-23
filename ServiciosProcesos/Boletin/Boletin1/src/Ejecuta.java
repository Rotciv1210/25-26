/*Escribe una clase llamada Ejecuta que reciba como argumentos el comando y las opciones del comando que se quiere ejecutar.

El programa debe crear un proceso hijo que ejecute el comando con las opciones correspondientes mostrando un mensaje de error
en el caso de que no se realizase correctamente la ejecución.
El padre debe esperar a que el hijo termine de informar si se produjo alguna anomalía en la ejecución del hijo.*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase Ejecuta: ejecuta un comando en Windows mediante un proceso hijo.
 * El proceso padre espera a que termine e informa si hubo errores.
 * Diseñado exclusivamente para sistemas Windows.
 */
public class Ejecuta {

    private String comando;         // Comando a ejecutar (ej. "dir", "ping", "ipconfig")
    private List<String> opciones;  // Opciones o argumentos adicionales

    /**
     * Constructor: recibe el comando y sus opciones.
     * @param comando Nombre del comando (ej. "dir", "ping")
     * @param opciones Opciones del comando (ej. "/w", "google.com", etc.)
     */
    public Ejecuta(String comando, String... opciones) {
        this.comando = comando;
        this.opciones = new ArrayList<>();
        for (String opt : opciones) {
            this.opciones.add(opt);
        }
    }

    /**
     * Ejecuta el comando en un proceso hijo usando cmd.exe.
     * El padre espera a que termine y muestra mensajes de error si ocurren.
     */
    public void ejecutar() {
        // Preparamos el comando para ejecutarlo en Windows: "cmd /c comando [opciones]"
        List<String> comandoCompleto = new ArrayList<>();
        comandoCompleto.add("cmd");     // Ejecutamos el intérprete de comandos de Windows
        comandoCompleto.add("/c");      // Le decimos que ejecute el comando y luego termine
        comandoCompleto.add(comando);   // Añadimos el comando principal
        comandoCompleto.addAll(opciones); // Añadimos todas las opciones

        // Creamos el ProcessBuilder con el comando listo
        ProcessBuilder pb = new ProcessBuilder(comandoCompleto);

        try {
            // Iniciamos el proceso hijo
            Process proceso = pb.start();

            // Leemos y mostramos la salida estándar (lo que normalmente ves en consola)
            BufferedReader salida = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String linea;
            while ((linea = salida.readLine()) != null) {
                System.out.println(linea);
            }

            // Leemos y mostramos la salida de error (importante si el comando falla)
            BufferedReader errores = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
            while ((linea = errores.readLine()) != null) {
                System.err.println("[ERROR DEL COMANDO] " + linea);
            }

            // El padre espera a que el hijo termine
            int codigoSalida = proceso.waitFor();

            // Informamos si hubo anomalías en la ejecución del hijo
            if (codigoSalida == 0) {
                System.out.println("\nComando ejecutado correctamente.");
            } else {
                System.err.println("\nError: el comando falló. Código de salida: " + codigoSalida);
            }

        } catch (IOException e) {
            // Error al intentar ejecutar el proceso (comando no encontrado, ruta inválida, etc.)
            System.err.println("Error de E/S: No se pudo ejecutar el comando. " + e.getMessage());
        } catch (InterruptedException e) {
            // El hilo fue interrumpido mientras esperaba al proceso hijo
            System.err.println(" El proceso fue interrumpido: " + e.getMessage());
            Thread.currentThread().interrupt(); // Restauramos el estado de interrupción
        }
    }

    public static void main(String[] args) {
        // Ejemplo 1: Listar archivos en formato ancho
        Ejecuta ejecutor1 = new Ejecuta("dir", "/w");
        System.out.println("=== Ejecutando: dir /w ===");
        ejecutor1.ejecutar();

        System.out.println(); // Línea en blanco para separar

        // Ejemplo 2: Hacer ping a localhost
        Ejecuta ejecutor2 = new Ejecuta("ping", "localhost", "-n", "2");
        System.out.println("=== Ejecutando: ping localhost -n 2 ===");
        ejecutor2.ejecutar();

        System.out.println(); // Línea en blanco para separar

        // Ejemplo 3: Probar un comando inexistente (para ver manejo de error)
        Ejecuta ejecutor3 = new Ejecuta("comando_que_no_existe");
        System.out.println("=== Ejecutando: comando_que_no_existe ===");
        ejecutor3.ejecutar();
    }
}