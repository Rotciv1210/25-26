import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ContadordeExes {
    
    public static void main(String[] args) throws IOException{

        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "tasklist");
        Process process = pb.start();
        int contador = 0;

        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String linea;
        System.out.println("Numero de .exe: " + Arrays.toString(args)+ ":");

        while((linea = br.readLine())!= null){

            if(linea.contains(".exe")){

               // System.out.println(linea);
                contador++;
            }
        }
        
                System.out.println(contador);
    }
}