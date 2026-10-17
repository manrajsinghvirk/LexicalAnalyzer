import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private final String input;
    private int pos = 0;

    public Lexer(String input) {
        this.input = input;
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();

        while (pos < input.length()) {
            char current = input.charAt(pos);

            // Skip Whitespace
            if (Character.isWhitespace(current)) {
                pos++;
                continue;
            }

            // Identify Numbers
            if (Character.isDigit(current)) {
                tokens.add(new Token(TokenType.NUMBER, readNumber()));
                continue;
            }

            // Identify Keywords and Identifiers (Letters)
            if (Character.isLetter(current)) {
                String word = readWord();
                if (word.equals("for") || word.equals("to") || word.equals("length")) {
                    tokens.add(new Token(TokenType.KEYWORD, word));
                } else {
                    tokens.add(new Token(TokenType.IDENTIFIER, word));
                }
                continue;
            }

            // Identify Operators
            if (current == '=' || current == '+' || current == '-' || current == '/') {
                tokens.add(new Token(TokenType.OPERATOR, String.valueOf(current)));
                pos++;
                continue;
            }

            // Identify Punctuation
            if ("[]{};.,".indexOf(current) != -1) {
                tokens.add(new Token(TokenType.PUNCTUATION, String.valueOf(current)));
                pos++;
                continue;
            }

            //  Error Handling for Invalid Inputs
            System.err.println("Lexical Error: Unrecognized character '" + current + "' at position " + pos);
            tokens.add(new Token(TokenType.UNKNOWN, String.valueOf(current)));
            pos++;
        }
        
        tokens.add(new Token(TokenType.EOF, ""));
        return tokens;
    }

    // Helper method to extract full numbers
    private String readNumber() {
        StringBuilder sb = new StringBuilder();
        while (pos < input.length() && Character.isDigit(input.charAt(pos))) {
            sb.append(input.charAt(pos));
            pos++;
        }
        return sb.toString();
    }

    // Helper method to extract full words
    private String readWord() {
        StringBuilder sb = new StringBuilder();
        while (pos < input.length() && Character.isLetterOrDigit(input.charAt(pos))) {
            sb.append(input.charAt(pos));
            pos++;
        }
        return sb.toString();
    }
}