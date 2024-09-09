import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import java.io.File;
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

            if (fileName.endsWith(".csv")) {
                List<Person> persons = readFromCSV(fileName);
                displayPersons(persons);
            } else if (fileName.endsWith(".json")) {
                List<Person> persons = readFromJSON(fileName);
                displayPersons(persons);
            } else if (fileName.endsWith(".xml")) {
                List<Person> persons = readFromXML(fileName);
                displayPersons(persons);
            } else {
                System.out.println("Unsupported file format.");
            }

        } else {
            System.out.println("No file selected. Exiting program.");
        }
    }

    private static void displayPersons(List<Person> persons) {
        System.out.printf("%-10s %-15s %-15s %-10s %-4s%n", "ID#", "Firstname", "Lastname", "Title", "YOB");
        System.out.println("==============================================================");

        for (Person person : persons) {
            System.out.printf("%-10s %-15s %-15s %-10s %-4d%n",
                    person.getId(), person.getFirstName(), person.getLastName(),
                    person.getTitle(), person.getYearOfBirth());
        }
    }

    private static List<Person> readFromCSV(String fileName) {
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
            System.out.println("An error occurred while reading the CSV data.");
            e.printStackTrace();
        }

        return persons;
    }

    private static List<Person> readFromJSON(String fileName) {
        List<Person> persons = new ArrayList<>();
        Path path = Path.of(fileName);

        try (Scanner scanner = new Scanner(Files.newBufferedReader(path))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.replaceAll("[{}\"]", "").split(",\\s*");
                String id = fields[0].split(":")[1].trim();
                String firstName = fields[1].split(":")[1].trim();
                String lastName = fields[2].split(":")[1].trim();
                String title = fields[3].split(":")[1].trim();
                int yearOfBirth = Integer.parseInt(fields[4].split(":")[1].trim());
                persons.add(new Person(id, firstName, lastName, title, yearOfBirth));
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the JSON data.");
            e.printStackTrace();
        }

        return persons;
    }

    private static List<Person> readFromXML(String fileName) {
        List<Person> persons = new ArrayList<>();
        try {
            File xmlFile = new File(fileName);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("Person");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element personElement = (Element) nodeList.item(i);

                String id = personElement.getElementsByTagName("ID").item(0).getTextContent();
                String firstName = personElement.getElementsByTagName("FirstName").item(0).getTextContent();
                String lastName = personElement.getElementsByTagName("LastName").item(0).getTextContent();
                String title = personElement.getElementsByTagName("Title").item(0).getTextContent();
                int yearOfBirth = Integer.parseInt(personElement.getElementsByTagName("YearOfBirth").item(0).getTextContent());

                persons.add(new Person(id, firstName, lastName, title, yearOfBirth));
            }
        } catch (Exception e) {
            System.out.println("An error occurred while reading the XML data.");
            e.printStackTrace();
        }

        return persons;
    }
}

