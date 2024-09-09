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

public class ProductReader {
    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new java.io.File("."));
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            java.io.File selectedFile = fileChooser.getSelectedFile();
            String fileName = selectedFile.getPath();

            if (fileName.endsWith(".csv")) {
                List<Product> products = readFromCSV(fileName);
                displayProducts(products);
            } else if (fileName.endsWith(".json")) {
                List<Product> products = readFromJSON(fileName);
                displayProducts(products);
            } else if (fileName.endsWith(".xml")) {
                List<Product> products = readFromXML(fileName);
                displayProducts(products);
            } else {
                System.out.println("Unsupported file format.");
            }

        } else {
            System.out.println("No file selected. Exiting program.");
        }
    }

    public static void displayProducts(List<Product> products) {
        System.out.printf("%-10s %-15s %-15s %-10s%n", "ID#", "Product Name", "Description", "Cost");
        System.out.println("==============================================================");

        for (Product product : products) {
            System.out.printf("%-10s %-15s %-15s %-10.2f%n",
                    product.getId(), product.getProductName(), product.getDescription(),
                    product.getCost());
        }
    }

    private static List<Product> readFromCSV(String fileName) {
        List<Product> products = new ArrayList<>();
        Path path = Path.of(fileName);

        try (Scanner scanner = new Scanner(Files.newBufferedReader(path))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(",\\s*");
                if (fields.length == 4) {
                    String id = fields[0];
                    String productName = fields[1];
                    String description = fields[2];
                    double cost = Double.parseDouble(fields[3]);
                    products.add(new Product(id, productName, description, cost));
                } else {
                    System.out.println("Invalid record: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the data.");
            e.printStackTrace();
        }

        return products;
    }

    private static List<Product> readFromJSON(String fileName) {
        List<Product> products = new ArrayList<>();
        Path path = Path.of(fileName);
        try (Scanner scanner = new Scanner(Files.newBufferedReader(path))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(",\\s*");
                if (fields.length == 4) {
                    String id = fields[0];
                    String productName = fields[1];
                    String description = fields[2];
                    double cost = Double.parseDouble(fields[3]);
                    products.add(new Product(id, productName, description, cost));
                } else {
                    System.out.println("Invalid record: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the data.");
            e.printStackTrace();
        }
        return products;
    }
    private static List<Product> readFromXML(String fileName) {
        List<Product> products = new ArrayList<>();
        try {
            File xmlFile = new File(fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("Product");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                String id = element.getAttribute("id");
                String productName = element.getElementsByTagName("productName").item(0).getTextContent();
                String description = element.getElementsByTagName("description").item(0).getTextContent();
                double cost = Double.parseDouble(element.getElementsByTagName("cost").item(0).getTextContent());
                products.add(new Product(id, productName, description, cost));
            }
        } catch (Exception e) {
            System.out.println("An error occurred while reading the data.");
            e.printStackTrace();
        }
        return products;
    }
}