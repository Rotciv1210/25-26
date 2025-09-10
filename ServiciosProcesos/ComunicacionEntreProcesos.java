import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ComunicacionEntreProcesos{
	public static void main(String[] args) throws IOException{
		Process process = new ProcessBuilder(args).start();
		InputStream is = process.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line;
		System. out.println ("Salida del proceso " + Arrays.toString(args) + ":");
		while ((line = br.readLine())!= null){
            
            String [] partes = line.split("\\s+"); //esta linea podria ir dentro del try pero no es aconsejable

            if(partes.length == 5){
                String puerto = partes[2].split(":")[1];
                System.out.println(puerto);
            }
			//System.out.println(line);
		}
	}
}