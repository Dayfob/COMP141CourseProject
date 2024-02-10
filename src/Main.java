// Phase 1.1
// Alikhan Semembayev
// Henglay Eung
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println(System.getProperty("user.dir"));
            FileReader reader = new FileReader("src/test_input.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter("src/test_out.txt", true);
            writer.write("Hello World");
            writer.write("\r\n");   // write new line
            writer.write("Good Bye!");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}