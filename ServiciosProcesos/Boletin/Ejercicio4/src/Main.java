import java.util.Scanner;

public class Main {

    // Letras válidas para calcular el control del DNI en España
    private static final String LETRAS_DNI = "TRWAGMYFPDXBNJZSQVHLCKE";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Introduce tu DNI (8 números + letra): ");
            String dni = sc.nextLine();

            if (!verificarDNI(dni)) {
                System.err.println("DNI incorrecto: " + dni);
                System.exit(-1);
            }

            System.out.print("Introduce tu IBAN: ");
            String iban = sc.nextLine();

            if (!verificarIBAN(iban)) {
                System.err.println("IBAN incorrecto: " + iban);
                System.exit(-1);
            }

            System.out.println("Datos correctos. Proceso finalizado con éxito.");
            System.exit(0);

        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            System.exit(-1);
        } finally {
            sc.close();
        }
    }

    /**
     * Verifica si un DNI español es válido.
     * Formato: 8 dígitos + letra correcta.
     */
    public static boolean verificarDNI(String dni) {
        if (dni == null || dni.trim().isEmpty()) return false;

        dni = dni.trim().toUpperCase();

        if (!dni.matches("\\d{8}[A-Z]")) return false;

        int numero = Integer.parseInt(dni.substring(0, 8));
        char letraCorrecta = LETRAS_DNI.charAt(numero % 23);
        char letraUsuario = dni.charAt(8);

        return letraCorrecta == letraUsuario;
    }

    /**
     * Verifica si un IBAN es válido (formato básico y dígito de control).
     */
    public static boolean verificarIBAN(String iban) {
        if (iban == null || iban.trim().isEmpty()) return false;

        iban = iban.replaceAll("\\s+", "").toUpperCase();

        if (!iban.matches("^[A-Z]{2}\\d{2}[A-Z0-9]{1,30}$")) return false;

        // Mover los 4 primeros caracteres al final
        String reformatted = iban.substring(4) + iban.substring(0, 4);

        // Convertir letras a números (A=10, B=11, ..., Z=35)
        StringBuilder sb = new StringBuilder();
        for (char c : reformatted.toCharArray()) {
            if (Character.isLetter(c)) {
                sb.append((int) c - 55);
            } else {
                sb.append(c);
            }
        }

        // Validación módulo 97
        return mod97(sb.toString()) == 1;
    }

    // Calcula el mod 97 de un número muy largo para validar el IBAN
    private static int mod97(String input) {
        int checksum = 0;
        String fragment;

        for (int i = 0; i < input.length(); i += 7) {
            fragment = checksum + input.substring(i, Math.min(i + 7, input.length()));
            checksum = Integer.parseInt(fragment) % 97;
        }
        return checksum;
    }
}
