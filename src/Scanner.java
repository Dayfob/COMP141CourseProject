// Phase 1.1
// Alikhan Semembayev
// Henglay Eung

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scanner {
    String InputString;
    char errorSymbol = ' ';
    List<Token> tokens;
    Pattern IDENTIFIER = Pattern.compile("([a-z]|[A-Z])([a-z]|[A-Z]|[0-9])*");
    Pattern NUMBER = Pattern.compile("[0-9]+");
    Pattern SYMBOL = Pattern.compile("[+\\-*/()]");
    Pattern WHITE_SPACE = Pattern.compile("\\s*");

    public Scanner() {

    }

    public void setInputString(String string) {
        this.InputString = string;
    }

    public char getErrorSymbol() {
        return errorSymbol;
    }

    public List<Token> getTokens() {
        this.errorSymbol = ' ';
        tokens = new ArrayList<>();
        String type;
        int i = 0;

        // Loop through the input string
        while (i < this.InputString.length()) {
            // If a white space is met, skip it
            if (WHITE_SPACE.matcher(this.InputString.charAt(i) + "").matches()) {
                i++;
                continue;
            }

            Token token = new Token();
            // add the character as a token
            token.addElement(this.InputString.charAt(i));

            Matcher matchID = IDENTIFIER.matcher(token.getValue());
            Matcher matchNum = NUMBER.matcher(token.getValue());
            Matcher matchSym = SYMBOL.matcher(token.getValue());

            int j = i + 1;

            // Identifier is matched
            if (matchID.matches()) {
                type = "IDENTIFIER";
                token.setType(type);

                // Check next character
                while (j < this.InputString.length()) {
                    if (WHITE_SPACE.matcher(this.InputString.charAt(i) + "").matches()) {
                        j++;
                        break;
                    }
                    // Concat to the previous character
                    token.addElement(this.InputString.charAt(j));
                    matchID = IDENTIFIER.matcher(token.getValue());

                    // Remove the concatenated character if the token does not match
                    if (!matchID.matches()) {
                        token.removeLastElement();
                        break;
                    }
                    j++;
                }
            // Number is matched
            } else if (matchNum.matches()) {
                type = "NUMBER";
                token.setType(type);

                // Check next character
                while (j < this.InputString.length()) {
                    if (WHITE_SPACE.matcher(this.InputString.charAt(i) + "").matches()) {
                        j++;
                        break;
                    }
                    // Concat to the previous character
                    token.addElement(this.InputString.charAt(j));
                    matchNum = NUMBER.matcher(token.getValue());

                    // Remove the concatenated character if the token does not match
                    if (!matchNum.matches()) {
                        token.removeLastElement();
                        break;
                    }
                    j++;
                }
            // Symbol is matched
            } else if (matchSym.matches()) {
                type = "SYMBOL";
                token.setType(type);

                // Check next character
                while (j < this.InputString.length()) {
                    if (WHITE_SPACE.matcher(this.InputString.charAt(i) + "").matches()) {
                        j++;
                        break;
                    }
                    // Concat to the previous character
                    token.addElement(this.InputString.charAt(j));
                    matchSym = SYMBOL.matcher(token.getValue());

                    // Remove the concatenated character if the token does not match
                    if (!matchSym.matches()) {
                        token.removeLastElement();
                        if (WHITE_SPACE.matcher(this.InputString.charAt(i) + "").matches()) {
                            j++;
                        }
                        break;
                    }
                    j++;
                }
            // No token is matched
            } else {
                token.removeLastElement();
                this.errorSymbol = this.InputString.charAt(i);
                break;
            }
            tokens.add(token);
            i = j;
        }
        return this.tokens;
    }
}
