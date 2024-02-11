import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

// Phase 1.1
// Alikhan Semembayev
// Henglay Eung
public class Scanner {
    String identifierRegex = "([a-z]|[A-Z])([a-z]|[A-Z]|[0-9])*";

    String numberRegex = "[0-9]+";

    String symbolRegex = "\\+|\\-|\\*|\\/|\\(|\\)";


    public List<Token> parseToken(String inputString) throws Exception {
        List<Token> tokenList = new ArrayList<>();
        StringBuilder tokenValueBuilder = new StringBuilder();
        String remainingChar = "";
        for (int i = 0; i < inputString.length(); i++) {
            Pattern identifierPattern = Pattern.compile(identifierRegex);

            Pattern numberPattern = Pattern.compile(numberRegex);

            Pattern symbolPattern = Pattern.compile(symbolRegex);

            tokenValueBuilder.append(remainingChar);
            remainingChar = "";

            tokenValueBuilder.append(inputString.charAt(i));
            if (identifierPattern.matcher(tokenValueBuilder.toString()).matches() || numberPattern.matcher(tokenValueBuilder.toString()).matches() || symbolPattern.matcher(tokenValueBuilder.toString()).matches()) {

            } else {
                if (identifierPattern.matcher(inputString.charAt(i) + "").matches() || numberPattern.matcher(inputString.charAt(i) + "").matches() || symbolPattern.matcher(inputString.charAt(i) + "").matches()) {
                    remainingChar = inputString.charAt(i) + "";
                }  else {

                    if (inputString.charAt(i) != ' ') {
                        System.out.println("inputString is " + inputString.charAt(i));
                        throw new Exception("Error char is " + inputString.charAt(i));
                    }

                }

                tokenValueBuilder.deleteCharAt(tokenValueBuilder.length() - 1);
                Token token = new Token();
                String tokenValue = tokenValueBuilder.toString();
                String tokenType = identifyTokenType(tokenValue);
                token.setValue(tokenValue);
                token.setType(tokenType);
                tokenList.add(token);
                tokenValueBuilder = new StringBuilder();
            }
        }
        Token token = new Token();
        String tokenValue = tokenValueBuilder.toString();
        String tokenType = identifyTokenType(tokenValue);
        token.setValue(tokenValue);
        token.setType(tokenType);
        tokenList.add(token);
        tokenValueBuilder = new StringBuilder();
        return tokenList;
    }
    private String identifyTokenType(String tokenValue) {
        Pattern identifierPattern = Pattern.compile(identifierRegex);

        Pattern numberPattern = Pattern.compile(numberRegex);

        Pattern symbolPattern = Pattern.compile(symbolRegex);
        if (identifierPattern.matcher(tokenValue).matches()) {
            return "IDENTIFIER";
        } else if (numberPattern.matcher(tokenValue).matches()) {
            return "NUMBER";
        } else if (symbolPattern.matcher(tokenValue).matches()) {
            return "SYMBOL";
        } else {
            return "ERROR";
        }
    }
}
