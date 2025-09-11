import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ejercicio2 {
    

    public static void main(String[] args) {


            Scanner scanner = new Scanner(System.in);

        while(true){
        System.out.println("=== MENÚ PRINCIPAL ===");
        System.out.println("1. DLL");
        System.out.println("2. EXE");
        System.out.println("3. Salir del programa");
        System.out.print("Elige una opción (1-2-3): ");
        
        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
            try {
                //ContadorDLL.main(args);
                ProcessBuilder pb = new ProcessBuilder("cmd.exe","/c", "tasklist /m");
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

                System.out.println("Hay "+contador+" DLL");
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("Error ejecutando el contador de DLL"+ e.getMessage());
            }
                break;
            case 2:
            try{
                //ContadordeExes.main(args);
                ProcessBuilder pb = new ProcessBuilder("cmd.exe","/c", "tasklist");
                Process process = pb.start();
                int contador = 0;

                InputStream is = process.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String linea;

                while((linea = br.readLine())!= null){

                    if(linea.contains(".exe")){
                        contador++;
                    }
                }

                System.out.println("Hay "+contador+" EXE");
            } catch(IOException e){
                System.out.println("Error ejecutando el contador de EXE"+e.getMessage());
            }
            break;
            default:
                System.out.println("Opción no válida. Por favor elige 1 o 2.");
                break;
            case 3:
            System.out.println("Saliendo del programa");
            scanner.close();
            System.exit(0);
            break;
        }
    }

    }


    
}
