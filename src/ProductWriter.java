import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductWriter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Product> products = new ArrayList<>();
        boolean moreProducts = true;

        while (moreProducts) {
            String id = SafeInput.getNonZeroLenString(scanner, "Enter ID");
            String productName = SafeInput.getNonZeroLenString(scanner, "Enter Product Name");
            String descriptions = SafeInput.getNonZeroLenString(scanner, "Enter Description");
            Double cost = SafeInput.getDouble(scanner, "Enter Cost");

            Product product = new  Product(id, productName, descriptions, cost);
            products.add(product);


            moreProducts = SafeInput.getYNConfirm(scanner, "Do you want to add another product?");
        }

        String fileName = SafeInput.getNonZeroLenString(scanner, "Enter the file name to save the data");
        String format = SafeInput.getRegExString(scanner, "Enter format (CSV, JSON, or XML)", "^(CSV|JSON|XML)$");

        switch (format.toUpperCase()) {
            case "CSV":
                saveToCSV(products, fileName + ".csv");
                break;
            case "JSON":
                saveAsJSON(products, fileName + ".json");
                break;
            case "XML":
                saveAsXML(products, fileName + ".xml");
                break;
            default:
                System.out.println("Invalid format.");
                break;
        }

    }

    private static void saveToCSV(ArrayList<Product> products, String fileName) {
        Path path = Path.of(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE)) {
            for (Product product : products) {
                writer.write(product.toCSV());
                writer.newLine();
            }
            System.out.println("Data saved to " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the data.");
            e.printStackTrace();
        }
    }
     public static void saveAsJSON(ArrayList<Product> products, String fileName) {
         Path path = Path.of(fileName);
         try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE)) {
             writer.write("[");
             writer.newLine();
             for (Product product : products) {
                 writer.write(product.toJSON());
                 writer.newLine();
             }
             writer.write("]");
             System.out.println("Data saved to " + fileName);
         } catch (IOException e) {
             System.out.println("An error occurred while saving the data.");
             e.printStackTrace();
         }
     }
     public static void saveAsXML(ArrayList<Product> products, String fileName) {
         Path path = Path.of(fileName);
         try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE)) {
             writer.write("<Products>");
             writer.newLine();

             for (Product product : products) {
                 writer.write(product.toXML());
                 writer.newLine();
             }
             writer.write("</Products>");
             writer.newLine();

             System.out.println("Data saved to " + fileName);
         } catch (IOException e) {
             System.out.println("An error occurred while saving the data.");
             e.printStackTrace();
         }
     }
}
