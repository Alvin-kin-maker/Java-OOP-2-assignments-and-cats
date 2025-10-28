import java.io.Console;
import java.util.Scanner;

public class LoginSystem {
    public static void main(String[] args) {
        String correctUsername = "admin";
        String correctPassword = "12345";
        Scanner input = new Scanner(System.in);

        int attempts = 3; // User has 3 tries

        while (attempts > 0) {
            System.out.print("Enter Username: ");
            String username = input.nextLine();

            String password = "";

            Console console = System.console();
            if (console != null) {
                // This works in real consoles (like CMD, Terminal, etc.)
                char[] passArray = console.readPassword("Enter Password: ");
                password = new String(passArray);
                System.out.println("*****"); // Shows stars after typing
            } else {
                // Fallback for Programiz/IDEs
                System.out.print("Enter Password: ");
                password = maskInput(input);
            }

            if (username.equals(correctUsername) && password.equals(correctPassword)) {
                System.out.println("\nLogin Successful!");
                return; // End program
            } else {
                attempts--;
                System.out.println("\nIncorrect credentials. Attempts left: " + attempts);
            }
        }

        System.out.println("\nAccount Locked! Too many failed attempts.");
    }

    // Fallback star-mask method for Programiz and IDEs
    private static String maskInput(Scanner input) {
        StringBuilder password = new StringBuilder();
        try {
            while (true) {
                char ch = (char) System.in.read();
                if (ch == '\n' || ch == '\r') {
                    System.out.println(); // Move to next line after Enter
                    break;
                }
                password.append(ch);
                System.out.print("*");
            }
        } catch (Exception e) {
            return input.nextLine(); // fallback to normal input
        }
        return password.toString().trim();
    }
}
