// Phase 1.1
// Alikhan Semembayev
// Henglay Eung

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println(System.getProperty("user.dir"));
            FileReader reader = new FileReader("src/test_input.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            Scanner scanner = new Scanner(null);

            try {
                FileWriter writer = new FileWriter("src/test_out.txt", true);

                while ((line = bufferedReader.readLine()) != null) {
                    scanner.setInputString(line);
                    writer.write("Line: " + line + "\r\n");

                    for (Token token : scanner.getTokens()) {
                        writer.write(token.getValue() + " : " + token.getType() + "\r\n");
                    }

                    if (scanner.getErrorSymbol() != ' ') {
                        writer.write("ERROR READING '" + scanner.getErrorSymbol() + "'" + "\r\n");
                    }
                    writer.write("\r\n");   // write new line
                }
                writer.close();
                reader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}