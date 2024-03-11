// Phase 2.1
// Alikhan Semembayev
// Henglay Eung

import java.util.List;

public class Parser {

    private int cursor = 0;
    private List<Token> tokens;

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    public Tree parse() {
        this.cursor = 0;
        Tree tree = parseExp();

        if (!this.checkBrackets()) {
            throw new RuntimeException("Grammatical error while parsing the expression");
        }

        return tree;
    }

    private Tree parseExp() {
        Tree tree = parseTerm();

        if (tokens.size() > cursor) {
            Token token = tokens.get(cursor);
            while (token.getValue().equals("+")) {
                cursor++;
                tree = new Tree(new Token("+", Scanner.TYPE_SYMBOL), tree, null, parseTerm());
                if (tokens.size() > cursor) {
                    token = tokens.get(cursor);
//                     cursor++;
                } else {
                    break;
                }
            }
        }
        return tree;
    }

    private Tree parseTerm() {
        Tree tree = parseFactor();
        if (tokens.size() > cursor) {
            Token token = tokens.get(cursor);
            while (token.getValue().equals("-")) {
                cursor++;
                tree = new Tree(new Token("-", Scanner.TYPE_SYMBOL), tree, null, parseFactor());
                if (tokens.size() > cursor) {
//                    cursor++;
                    token = tokens.get(cursor);
                } else {
                    break;
                }
            }
        }
        return tree;
    }

    private Tree parseFactor() {
        Tree tree = parsePiece();

        if (tokens.size() > cursor) {
            Token token = tokens.get(cursor);
            while (token.getValue().equals("/")) {
                cursor++;
                tree = new Tree(new Token("/", Scanner.TYPE_SYMBOL), tree, null, parsePiece());
                if (tokens.size() > cursor) {
                    token = tokens.get(cursor);
                } else {
                    break;
                }
            }
        }
        return tree;
    }

    private Tree parsePiece() {
        Tree tree = parseElement();

        if (tokens.size() > cursor) {
            Token token = tokens.get(cursor);
            while (token.getValue().equals("*")) {
                cursor++;
                tree = new Tree(new Token("*", Scanner.TYPE_SYMBOL), tree, null, parseElement());
                if (tokens.size() > cursor) {
//                    cursor++;
                    token = tokens.get(cursor);
                } else {
                    break;
                }
            }
            if (cursor <= 0) {
                if (token.getValue().equals(")")) {
                    throw new RuntimeException("Grammatical error while parsing the expression");
                }
            } else if (cursor >= tokens.size() - 1) {
                if (token.getValue().equals("(")) {
                    throw new RuntimeException("Grammatical error while parsing the expression");
                }
            } else {
                Token tokenPerv = tokens.get(cursor - 1);
                Token tokenNext = tokens.get(cursor + 1);
                if (token.getValue().equals(")") && !tokenNext.getType().equals(Scanner.TYPE_SYMBOL)) {
                    throw new RuntimeException("Grammatical error while parsing the expression");
                }
                if (token.getValue().equals("(") &&
                        (!tokenPerv.getType().equals(Scanner.TYPE_SYMBOL) || tokenPerv.getValue().equals(")"))) {
                    throw new RuntimeException("Grammatical error while parsing the expression");
                }
            }
        }

        return tree;
    }

    private Tree parseElement() {
        Tree tree;
        if (tokens.size() < cursor + 1) {
            throw new RuntimeException("Grammatical error while parsing the expression");
        }
        Token token = tokens.get(cursor);
        if (token.getType().equals(Scanner.TYPE_NUMBER) || token.getType().equals(Scanner.TYPE_IDENTIFIER)) {
            cursor++;
            tree = new Tree(token, null, null, null);
        } else if (token.getValue().equals("(")) {
            cursor++;
            tree = parseExp();
            if (tokens.size() > cursor && tokens.get(cursor).getValue().equals(")")) {
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

    private boolean checkBrackets() {
        int bracketsCounter = 0;
        for (Token token : tokens) {
            if (token.getValue().equals("(")) {
                bracketsCounter++;
            } else if (token.getValue().equals(")")) {
                bracketsCounter--;
            }
            if (bracketsCounter < 0) {
                return false;
            }
        }

        return bracketsCounter == 0;
    }
}
