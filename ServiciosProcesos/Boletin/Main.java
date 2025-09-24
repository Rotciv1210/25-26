import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            // Creamos el ProcessBuilder para ejecutar la clase Hijo
            // "java -cp . Hijo" → busca la clase Hijo en el classpath actual (.)
            ProcessBuilder pb = new ProcessBuilder("java", "-cp", ".", "Hijo");

            // Le indicamos que use como directorio de trabajo la carpeta actual
            // IMPORTANTE: si los .class están en otra carpeta, aquí debes poner esa ruta
            pb.directory(new File("."));

            // redirectErrorStream(false) → dejamos separado stdout y stderr
            // (si fuera true, los mezclaría en la misma salida)
            pb.redirectErrorStream(false);

            // Lanzamos el proceso hijo
            Process procesoHijo = pb.start();

            // Para escribir en la entrada estándar del hijo
            // (es decir, lo que en Hijo.java lee con System.in)
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(procesoHijo.getOutputStream()), true);

            // Para leer la salida estándar del hijo (System.out en Hijo.java)
            BufferedReader reader = new BufferedReader(new InputStreamReader(procesoHijo.getInputStream()));

            // Para leer la salida de error del hijo (System.err en caso de fallo)
            BufferedReader errReader = new BufferedReader(new InputStreamReader(procesoHijo.getErrorStream()));

            // Scanner para leer del teclado (entrada estándar del usuario)
            Scanner teclado = new Scanner(System.in);

            System.out.println("Escribe texto (Ctrl+C para salir):");

            // Bucle principal: leer lo que el usuario teclea
            while (teclado.hasNextLine()) {
                String linea = teclado.nextLine();

                // Enviamos la línea al hijo
                writer.println(linea);

                // Intentamos leer la respuesta del hijo
                String respuesta = reader.readLine();
                if (respuesta != null) {
                    System.out.println(respuesta);
                }

                // Si el hijo ha escrito errores, los mostramos en consola
                while (errReader.ready()) {
                    System.err.println("ERR hijo: " + errReader.readLine());
                }
            }

            // Cerramos teclado y matamos al proceso hijo al terminar
            teclado.close();
            procesoHijo.destroy();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
