import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileInputStreamEjemplo {
    public static void main(String[] args) {
        try {
            // Abre un FileInputStream para el archivo especificado
            FileInputStream fileInputStream = new FileInputStream("texto.txt");

            int byteLeido;
            
            // Lee cada byte del archivo
            while ((byteLeido = fileInputStream.read()) != -1) {

                if(byteLeido == 160){
                    System.out.print("á");
                    continue;
                }
                // Haz algo con el byte leído, como procesarlo o almacenarlo en otro lugar
                System.out.print((char) byteLeido); // Convierte el byte a un carácter para imprimirlo
            }

            // Cierra el FileInputStream cuando ya no se necesita
            fileInputStream.close();
        } catch (FileNotFoundException f){
            System.out.println("No encuentra el archivo");
        } 
         catch (IOException e) {
            e.printStackTrace();
        }
    }
}