import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ContadorDLL {

    public static void main(String[] args) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "tasklist /m");
        Process process = pb.start();
        int contador = 0;

        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String linea;
        
        while((linea = br.readLine())!= null){

            if(linea.contains(".dll")){
                contador++;
            }
        }

        System.out.println("Hay "+contador+ " DLL");
        
    }
    
}
