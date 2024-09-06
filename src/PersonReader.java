import javax.swing.JFileChooser;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PersonReader {
    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new java.io.File("."));
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            java.io.File selectedFile = fileChooser.getSelectedFile();
            String fileName = selectedFile.getPath();
            List<Person> persons = readFromFile(fileName);

            System.out.printf("%-10s %-15s %-15s %-10s %-4s%n", "ID#", "Firstname", "Lastname", "Title", "YOB");
            System.out.println("==============================================================");

            for (Person person : persons) {
                System.out.printf("%-10s %-15s %-15s %-10s %-4d%n",
                        person.getId(), person.getFirstName(), person.getLastName(),
                        person.getTitle(), person.getYearOfBirth());
            }

            } else {
                System.out.println("No file selected. Exiting program.");
            }
        }

    private static List<Person> readFromFile(String fileName) {
        List<Person> persons = new ArrayList<>();
        Path path = Path.of(fileName);

        try (Scanner scanner = new Scanner(Files.newBufferedReader(path))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(",\\s*");
                if (fields.length == 5) {
                    String id = fields[0];
                    String firstName = fields[1];
                    String lastName = fields[2];
                    String title = fields[3];
                    int yearOfBirth = Integer.parseInt(fields[4]);
                    persons.add(new Person(id, firstName, lastName, title, yearOfBirth));
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the data.");
            e.printStackTrace();
        }

        return persons;
    }
}

