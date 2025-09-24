import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        try{
            ProcessBuilder pb = new ProcessBuilder("java", "-cp", ".", "Hijo");
            pb.redirectErrorStream(true);
            Process procesoHijo = pb.start();

            PrintWriter pw = new PrintWriter(new OutputStreamWriter(procesoHijo.getOutputStream()));
            BufferedReader br = new BufferedReader(new InputStreamReader(procesoHijo.getInputStream()));

            Scanner teclado = new Scanner(System.in);
            System.out.println("Escribe texto (Ctrl + C para salir)");

            while(teclado.hasNextLine()){

                String linea = teclado.nextLine();

                pw.println(linea);

                String respuesta = br.readLine();
                if(respuesta != null){
                    System.out.println(respuesta);
                } else {
                    break;
                }
            }
            teclado.close();
            procesoHijo.destroy();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}