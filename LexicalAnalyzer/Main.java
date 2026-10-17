import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Lexical Analyzer Started. Type 'exit' to quit.");

        while (true) {
            System.out.print("\nEnter code to analyze > ");
            String input = scanner.nextLine();

            if (input.trim().equalsIgnoreCase("exit")) {
                System.out.println("Exiting analyzer...");
                break;
            }

            Lexer lexer = new Lexer(input);
            List<Token> tokens = lexer.tokenize();

            System.out.println("--- Token Output ---");
            for (Token token : tokens) {
                // If the lexer found an UNKNOWN character, it prints an error message
                if (token.type != TokenType.UNKNOWN && token.type != TokenType.EOF) {
                    System.out.println(token);
                }
            }
        }
        scanner.close();
    }
}