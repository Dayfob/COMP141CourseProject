// Phase 1.1
// Alikhan Semembayev
// Henglay Eung
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            FileReader reader = new FileReader(System.getProperty("user.dir") + "/src/main/java/test_input.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;

//            while ((line = bufferedReader.readLine()) != -1) {
//                System.out.println(line);
//            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}