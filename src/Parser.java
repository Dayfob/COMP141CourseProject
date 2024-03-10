// Phase 2.1
// Alikhan Semembayev
// Henglay Eung

import java.util.List;

public class Parser {

    private int cursor = 0;

    public Tree parse(List<Token> tokenList) {
        this.cursor = 0;
        return parseExp(tokenList);
    }

     private Tree parseExp(List<Token> tokenList) {
         Tree tree = parseTerm(tokenList);

         if (tokenList.size() > cursor) {
             Token token = tokenList.get(cursor);
             while (token.getValue().equals("+")) {
                 cursor++;
                 tree = new Tree(new Token("+", Scanner.TYPE_SYMBOL), tree, null, parseTerm(tokenList));
                 if (tokenList.size() > cursor) {
                     token = tokenList.get(cursor);
//                     cursor++;
                 } else {
                     break;
                 }
             }
         }
         return tree;
    }

    private Tree parseTerm(List<Token> tokenList) {
        Tree tree = parseFactor(tokenList);
        if (tokenList.size() > cursor) {
            Token token = tokenList.get(cursor);
            while (token.getValue().equals("-")) {
                cursor++;
                tree = new Tree(new Token("-", Scanner.TYPE_SYMBOL), tree, null, parseFactor(tokenList));
                if (tokenList.size() > cursor) {
//                    cursor++;
                    token = tokenList.get(cursor);
                } else {
                    break;
                }
            }
        }
        return tree;
    }

    private Tree parseFactor(List<Token> tokenList) {
        Tree tree = parsePiece(tokenList);

        if (tokenList.size() > cursor) {
            Token token = tokenList.get(cursor);
            while (token.getValue().equals("/")) {
                cursor++;
                tree = new Tree(new Token("/", Scanner.TYPE_SYMBOL), tree, null, parsePiece(tokenList));
                if (tokenList.size() > cursor) {
                    token = tokenList.get(cursor);
                } else {
                    break;
                }
            }
        }
        return tree;
    }

    private Tree parsePiece(List<Token> tokenList) {
        Tree tree = parseElement(tokenList);

            if (tokenList.size() > cursor) {
                Token token = tokenList.get(cursor);
                while (token.getValue().equals("*")) {
                    cursor++;
                    tree = new Tree(new Token("*", Scanner.TYPE_SYMBOL), tree, null, parseElement(tokenList));
                    if (tokenList.size() > cursor) {
//                    cursor++;
                        token = tokenList.get(cursor);
                    } else {
                        break;
                    }
                }
            }

        return tree;
    }

    private Tree parseElement(List<Token> tokenList) {
        Tree tree;
        if (tokenList.size() < cursor + 1) {
            throw new RuntimeException("Grammatical error while parsing the expression");
        }
        Token token = tokenList.get(cursor);
        if (token.getType().equals(Scanner.TYPE_NUMBER) || token.getType().equals(Scanner.TYPE_IDENTIFIER)) {
            cursor++;
            tree = new Tree(token, null, null, null);
        } else if (token.getValue().equals("(")) {
            cursor++;
            tree = parseExp(tokenList);
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
