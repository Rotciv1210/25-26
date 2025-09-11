import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Logs {

    public static void main(String[] args) {

        
        try(BufferedReader br = new BufferedReader(new FileReader("accesos.log"))){

            String linea;

            while((linea = br.readLine())!= null){

        Pattern patron = Pattern.compile("\\((.*?)\\)");
        Matcher match = patron.matcher(linea);

        while(match.find()){
            System.out.println(match.group());
        } 
            }
                
        } catch (IOException e ){
            System.out.println("Error: "+e.getMessage());
        }
    }
}
