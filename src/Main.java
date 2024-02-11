// Phase 1.1
// Alikhan Semembayev
// Henglay Eung
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            FileReader reader = new FileReader("src/test_input.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            FileWriter writer = new FileWriter("src/test_out.txt", true);
            String line;
            Scanner scanner = new Scanner();
            while ((line = bufferedReader.readLine()) != null) {
                try {
                    List<Token> tokenList = scanner.parseToken(line);
                    for (Token token: tokenList) {
                        System.out.println(token.getValue() + " : " + token.getType());
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }


//                writer.write(line);
//                writer.write("\r\n");   // write new line
//                writer.write("Good Bye!");
            }
            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}