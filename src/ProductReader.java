import javax.swing.JFileChooser;
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
            List<Product> products = readFromFile(fileName);

            System.out.printf("%-10s %-15s %-15s %-4s%n", "ID#", "Product Name", "Description", "Cost");
            System.out.println("==============================================================");

            for (Product product : products) {
                System.out.printf("%-10s %-15s %-15s %-4f%n",
                        product.getId(), product.getProductName(), product.getDescription(), product.getCost());
            }

        } else {
            System.out.println("No file selected. Exiting program.");
        }
    }

    private static List<Product> readFromFile(String fileName) {
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
}
