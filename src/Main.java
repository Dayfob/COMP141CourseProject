// Phase 1.2
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
                }
                // write new line
                writer.write("\r\n");
            }
            writer.close();
            reader.close();
            System.out.println("Scanning is finished");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}