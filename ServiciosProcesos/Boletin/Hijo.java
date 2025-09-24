import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Hijo {

    public static void main(String[] args) {
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter pw = new PrintWriter(System.out,true);

            String linea;
            while((linea = br.readLine())!=null){
                pw.println(linea.toUpperCase());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
