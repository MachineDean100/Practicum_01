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
        ArrayList<Person> persons = new ArrayList<>();
        boolean morePersons = true;

        while (morePersons) {
            String id = SafeInput.getNonZeroLenString(scanner, "Enter ID");
            String firstName = SafeInput.getNonZeroLenString(scanner, "Enter First Name");
            String lastName = SafeInput.getNonZeroLenString(scanner, "Enter Last Name");
            String title = SafeInput.getNonZeroLenString(scanner, "Enter Title");
            int yearOfBirth = SafeInput.getInt(scanner, "Enter Year of Birth");

            Person person = new Person(id, firstName, lastName, title, yearOfBirth);
            persons.add(person);

            morePersons = SafeInput.getYNConfirm(scanner, "Do you want to add another person?");
        }

        String fileName = SafeInput.getNonZeroLenString(scanner, "Enter the base file name (without extension)");

        String format = SafeInput.getRegExString(scanner, "Enter format (CSV, JSON, or XML)", "^(CSV|JSON|XML)$");

        switch (format.toUpperCase()) {
            case "CSV":
                saveToCSV(persons, fileName + ".csv");
                break;
            case "JSON":
                saveAsJSON(persons, fileName + ".json");
                break;
            case "XML":
                saveAsXML(persons, fileName + ".xml");
                break;
            default:
                System.out.println("Invalid format.");
                break;
        }
    }

    private static void saveToCSV(ArrayList<Person> persons, String fileName) {
        Path path = Path.of(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE)) {
            for (Person person : persons) {
                writer.write(person.toCSV());
                writer.newLine();
            }
            System.out.println("Data saved to " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the data.");
            e.printStackTrace();
        }
    }

    public static void saveAsJSON(ArrayList<Person> persons, String fileName) {
        Path path = Path.of(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE)) {
            for (Person person : persons) {
                writer.write(person.toJSON());  // Convert to JSON
                writer.newLine();
            }
            System.out.println("Data saved to JSON file: " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the data.");
            e.printStackTrace();
        }
    }
    public static void saveAsXML(ArrayList<Person> persons, String fileName) {
        Path path = Path.of(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE)) {
            writer.write("<Persons>");
            writer.newLine();

            for (Person person : persons) {
                writer.write(person.toXML());
                writer.newLine();
            }

            writer.write("</Persons>");
            writer.newLine();

            System.out.println("Data saved to XML file: " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the data.");
            e.printStackTrace();
        }
    }
}

