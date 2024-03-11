// Phase 2.1
// Alikhan Semembayev
// Henglay Eung

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        try {
            Pattern WHITE_SPACE = Pattern.compile("\\s*");
            String inputFileName = args[0];
            FileReader reader = new FileReader(inputFileName);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            Scanner scanner = new Scanner();

            String outputFileName = args[1];
            FileWriter writer = new FileWriter(outputFileName, true);

            System.out.println("Scanner is starting");
            // Read each line from the input file
            while ((line = bufferedReader.readLine()) != null) {
                // check white space before outputting to the file
                if (WHITE_SPACE.matcher(line).matches()) {
                    continue;
                }
                // Print the input line to the output file
                writer.write("Line: " + line + "\r\n");
                // Pass the input line to the scanner
                scanner.setInputString(line);
                // Retrieve tokens and print them to the output file
                for (Token token : scanner.getTokens()) {
                    writer.write(token.getValue() + " : " + token.getType() + "\r\n");
                }
                // Check for error
                if (scanner.getErrorSymbol() != ' ') {
                    writer.write("ERROR READING '" + scanner.getErrorSymbol() + "'" + "\r\n");
                } else {
                    Parser parser = new Parser();
                    writer.write("\nTree \r\n");

                    try {
                        Tree ast = parser.parse(scanner.getTokens());
                        printTree(writer, ast, 0);
                    } catch (RuntimeException e) {
                        writer.write(e.getMessage() + "\r\n");
                        System.out.println(e.getMessage());
                    }
                }
                // write new line
                writer.write("\r\n");
            }
            System.out.println("Scanning is finished");

            writer.close();
            reader.close();
            System.out.println("Parsing is finished");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printTree(FileWriter writer, Tree tree, int level) throws IOException {
        if (tree != null) {
            Token token = tree.getToken();
            writer.write(" ".repeat(level * 4));
            writer.write(token.getValue() + " : " + token.getType() + "\r\n");
            printTree(writer, tree.getLeftSubtree(), level + 1);
            printTree(writer, tree.getMiddleSubtree(), level + 1);
            printTree(writer, tree.getRightSubtree(), level + 1);
        }
    }
}