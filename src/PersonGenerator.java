import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> persons = new ArrayList<>();
        boolean morePersons = true;

        while (morePersons) {
            String id = SafeInput.getNonZeroLenString(scanner, "Enter ID");
            String firstName = SafeInput.getNonZeroLenString(scanner, "Enter First Name");
            String lastName = SafeInput.getNonZeroLenString(scanner, "Enter Last Name");
            String title = SafeInput.getNonZeroLenString(scanner, "Enter Title");
            int yearOfBirth = SafeInput.getInt(scanner, "Enter Year of Birth");

            String personRecord = String.format("%s, %s, %s, %s, %d", id, firstName, lastName, title, yearOfBirth);
            persons.add(personRecord);

            morePersons = SafeInput.getYNConfirm(scanner, "Do you want to add another person?");
        }

        String fileName = SafeInput.getNonZeroLenString(scanner, "Enter the file name to save the data");
        saveToFile(persons, fileName);
    }

    private static void saveToFile(ArrayList<String> persons, String fileName) {
        Path path = Path.of(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE)) {
            for (String person : persons) {
                writer.write(person);
                writer.newLine();
            }
            System.out.println("Data saved to " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the data.");
            e.printStackTrace();
        }
    }
}
