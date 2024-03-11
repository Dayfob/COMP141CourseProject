// Phase 2.1
// Alikhan Semembayev
// Henglay Eung

import java.util.List;

public class Parser {

    private int cursor = 0;

    public Tree parse(List<Token> tokenList) {
        this.cursor = 0;
        Tree tree = parseExp(tokenList);
        // Handling unexpected brackets
        if (cursor < tokenList.size()) {
            throw new RuntimeException("Error while parsing " + tokenList.get(cursor).getValue());
        }
        return tree;
    }

     private Tree parseExp(List<Token> tokenList) {
         Tree tree = parseTerm(tokenList);
         // Parse expression while next token is summation
         while (tokenList.size() > cursor && tokenList.get(cursor).getValue().equals("+")) {
             cursor++;
             tree = new Tree(new Token("+", Scanner.TYPE_SYMBOL), tree, null, parseTerm(tokenList));
         }
         return tree;
    }

    private Tree parseTerm(List<Token> tokenList) {
        Tree tree = parseFactor(tokenList);
        // Parse term while next token is subtraction
        while (tokenList.size() > cursor && tokenList.get(cursor).getValue().equals("-")) {
            cursor++;
            tree = new Tree(new Token("-", Scanner.TYPE_SYMBOL), tree, null, parseFactor(tokenList));
        }
        return tree;
    }

    private Tree parseFactor(List<Token> tokenList) {
        Tree tree = parsePiece(tokenList);
        // Parse factor while next token is division
        while (tokenList.size() > cursor && tokenList.get(cursor).getValue().equals("/")) {
            cursor++;
            tree = new Tree(new Token("/", Scanner.TYPE_SYMBOL), tree, null, parsePiece(tokenList));
        }
        return tree;
    }

    private Tree parsePiece(List<Token> tokenList) {
        Tree tree = parseElement(tokenList);
        // Parse piece while next token is multiplication
        while (tokenList.size() > cursor && tokenList.get(cursor).getValue().equals("*")) {
            cursor++;
            tree = new Tree(new Token("*", Scanner.TYPE_SYMBOL), tree, null, parseElement(tokenList));
        }
        return tree;
    }

    private Tree parseElement(List<Token> tokenList) {
        Tree tree;
        // Handling incomplete expressions
        if (tokenList.size() < cursor + 1) {
            throw new RuntimeException("Grammatical error while parsing the expression");
        }
        Token token = tokenList.get(cursor);
        // Create leaf for number or identifier
        if (token.getType().equals(Scanner.TYPE_NUMBER) || token.getType().equals(Scanner.TYPE_IDENTIFIER)) {
            cursor++;
            tree = new Tree(token, null, null, null);
        // Parsing expression in brackets
        } else if (token.getValue().equals("(")) {
            cursor++;
            tree = parseExp(tokenList);
            // Check if block ends with closing bracket
            if (tokenList.size() > cursor && tokenList.get(cursor).getValue().equals(")")) {
                cursor++;
                return tree;
            } else {
                throw new RuntimeException("Error while parsing element. No close parentheses");
            }
        } else {
            throw new RuntimeException("Error while parsing element. Token type not found");
        }
        return tree;
    }
}
