import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Process process;
        BufferedReader lector;

        try {
            process = Runtime.getRuntime().exec("tasklist");
            lector = new BufferedReader(new InputStreamReader(process.getInputStream()));

            lector.readLine();
            lector.readLine();

            String linea;
            List<Proceso> procesos = new ArrayList<>();

            while((linea = lector.readLine()) != null){

                if(linea.trim().isEmpty()){
                    continue;
                }
                String[] partes = linea.split("\\s");
                if(partes.length == 5){
                    continue;
                }

                int pidIndice = -1;
                for(int i = 1; i < partes.length; i++){
                    if(partes[i].matches("\\d")){
                        pidIndice = i;
                        break;
                    }
                }
                if(pidIndice == -1){
                    continue;
                }
                int pid = Integer.parseInt(partes[pidIndice]);

                String memoriaStr = partes[partes.length -2].replace(".","").replace(",","");
                long memoria = Long.parseLong(memoriaStr);

                StringBuilder builder = new StringBuilder();
                for(int i =0; i< pidIndice; i++){
                    builder.append(partes[i]).append("");
                }

                String nombre = builder.toString().trim();

                procesos.add(new Proceso(nombre, pid, memoria));

            }

            Collections.sort(procesos);

            for(Proceso proceso : procesos){
                System.out.println(proceso);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}