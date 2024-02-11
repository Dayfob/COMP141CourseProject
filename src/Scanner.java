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


    public Scanner(String input) {
        this.InputString = input;
    }

    public void setInputString(String string) {
        this.InputString = string;
    }

    public char getErrorSymbol() {
        return errorSymbol;
    }

    public List<Token> getTokens() {
        this.errorSymbol = ' ';
        tokens = new ArrayList<Token>();
        String type = null;
        int i = 0;

        while (i < this.InputString.length()) {
            if (this.InputString.charAt(i) == ' ') {
                continue;
            }

            Token token = new Token();
            token.addElement(this.InputString.charAt(i));

            Matcher matchID = IDENTIFIER.matcher(token.getValue());
            Matcher matchNum = NUMBER.matcher(token.getValue());
            Matcher matchSym = SYMBOL.matcher(token.getValue());

            int j = i + 1;

            if (matchID.matches()) {
                type = "IDENTIFIER";
                token.setType(type);

                while (j < this.InputString.length()) {
                    if (this.InputString.charAt(j) == ' ') {
                        j++;
                        break;
                    }
                    token.addElement(this.InputString.charAt(j));
                    matchID = IDENTIFIER.matcher(token.getValue());

                    if (!matchID.matches()) {
                        token.removeLastElement();
                        break;
                    }
                    j++;
                }
            } else if (matchNum.matches()) {
                type = "NUMBER";
                token.setType(type);

                while (j < this.InputString.length()) {
                    if (this.InputString.charAt(j) == ' ') {
                        j++;
                        break;
                    }
                    token.addElement(this.InputString.charAt(j));
                    matchNum = NUMBER.matcher(token.getValue());

                    if (!matchNum.matches()) {
                        token.removeLastElement();
                        break;
                    }
                    j++;
                }
            } else if (matchSym.matches()) {
                type = "SYMBOL";
                token.setType(type);

                while (j < this.InputString.length()) {
                    if (this.InputString.charAt(j) == ' ') {
                        j++;
                        break;
                    }
                    token.addElement(this.InputString.charAt(j));
                    matchSym = SYMBOL.matcher(token.getValue());

                    if (!matchSym.matches()) {
                        token.removeLastElement();
                        if (this.InputString.charAt(j) == ' ') {
                            j++;
                        }
                        break;
                    }
                    j++;
                }
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
