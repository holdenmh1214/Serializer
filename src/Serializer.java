import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by holdenhughes on 10/14/15.
 */
public class Serializer {
    static Book newBook;

    public static void main(String[] args) throws IOException {

        newBook = loadBook();

        if (newBook == null){
            questions();

        } else {
            System.out.println("Book Found");
            questions();
        }
    }

    static void saveBook() {
        File f = new File("save.json");
        JsonSerializer serializer = new JsonSerializer();
        String contentToSave = serializer.serialize(newBook);

        try {
            FileWriter fw = new FileWriter(f);
            fw.write(contentToSave);
            fw.close();
        } catch (Exception e) {

        }
    }

    static Book loadBook() {
        try {
            File f = new File("save.json");
            FileReader fr = new FileReader(f);
            int fileSize = (int) f.length();
            char[] contents = new char[fileSize];
            fr.read(contents);
            String fileContents = new String(contents);
            System.out.println(fileContents);
            JsonParser parser = new JsonParser();
            return parser.parse(fileContents, Book.class);

        } catch (Exception e) {
            System.out.println("Oops.. There was an error somewhere!");
            return null;
        }
    }

    static void questions(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to add or edit book? [y]/[n]");
        String answer;
        answer = scanner.nextLine().toLowerCase();

        if (answer.equals("y")) {
            newBook = new Book();
            System.out.println("What is your book title?");
            newBook.title = scanner.nextLine();

            System.out.println("Who is the author?");
            newBook.author = scanner.nextLine();

            System.out.println("What is the genre?");
            newBook.genre = scanner.nextLine();

            System.out.println("When was it published?");
            newBook.publishDate = scanner.nextLine();

            System.out.println("Who is the main protagonist?");
            newBook.protagonistName = scanner.nextLine();

            saveBook();

        } else if (answer.equals("n")){
            System.out.println("Exiting system");
            System.exit(0);
        } else {
            System.out.println("Invalid option");
            System.exit(0);
        }
    }
}