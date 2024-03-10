import java.util.List;

public class Parser {

    public Tree parse(List<Token> tokenList) {
        return parseExp(tokenList);
    }
    int cursor = 0;
     private Tree parseExp(List<Token> tokenList) {
         Tree tree = parseTerm(tokenList);

         if (tokenList.size() > cursor + 2) {
             Token token = tokenList.get(cursor);
             while (token.getValue().equals("+")) {
                 cursor++;
                 tree = new Tree(new Token("+", Scanner.TYPE_SYMBOL), tree, null, parseTerm(tokenList));
                 if (tokenList.size() > cursor + 2) {
                     cursor++;
                     token = tokenList.get(cursor);
                 } else {
                     break;
                 }

             }
         }
         return tree;
    }

    public Tree parseTerm(List<Token> tokenList) {
        Tree tree = parseFactor(tokenList);
        if (tokenList.size() > cursor + 2) {
            Token token = tokenList.get(cursor);
            while (token.getValue().equals("-")) {
                cursor++;
                tree = new Tree(new Token("-", Scanner.TYPE_SYMBOL), tree, null, parseFactor(tokenList));
                if (tokenList.size() > cursor + 2) {
                    cursor++;
                    token = tokenList.get(cursor);
                } else {
                    break;
                }

            }
        }
        return tree;
    }

    public Tree parseFactor(List<Token> tokenList) {
        Tree tree = parsePiece(tokenList);
        if (tokenList.size() > cursor + 2) {
            Token token = tokenList.get(cursor);
            while (token.getValue().equals("/")) {
                cursor++;
                tree = new Tree(new Token("/", Scanner.TYPE_SYMBOL), tree, null, parsePiece(tokenList));
                if (tokenList.size() > cursor + 2) {
                    token = tokenList.get(cursor);
                } else {
                    break;
                }

            }
        }
        return tree;
    }

    public Tree parsePiece(List<Token> tokenList) {
        Tree tree = parseElement(tokenList);
        if (tokenList.size() > cursor + 2) {
            Token token = tokenList.get(cursor);
            while (token.getValue().equals("*")) {
                cursor++;
                tree = new Tree(new Token("*", Scanner.TYPE_SYMBOL), tree, null, parseElement(tokenList));
                if (tokenList.size() > cursor + 2) {
                    cursor++;
                    token = tokenList.get(cursor);
                } else {
                    break;
                }

            }
        }
        return tree;
    }

    public Tree parseElement(List<Token> tokenList) {
        Tree tree;
        Token token = tokenList.get(cursor);
        if (token.getType().equals(Scanner.TYPE_NUMBER) || token.getType().equals(Scanner.TYPE_IDENTIFIER)) {
            cursor++;
            tree = new Tree(token, null, null, null);
        } else if (token.getValue().equals("(")) {
            cursor++;
            tree = parseExp(tokenList);
            if (tokenList.get(cursor).equals(")")) {
                cursor++;
                return tree;
            } else {

            }
        } else {
            throw new RuntimeException();
        }
        return tree;
    }
}
