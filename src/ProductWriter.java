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
        ArrayList<String> products = new ArrayList<>();
        boolean moreProducts = true;

        while (moreProducts) {
            String id = SafeInput.getNonZeroLenString(scanner, "Enter ID");
            String productName = SafeInput.getNonZeroLenString(scanner, "Enter Product Name");
            String descriptions = SafeInput.getNonZeroLenString(scanner, "Enter Description");
            Double cost = SafeInput.getDouble(scanner, "Enter Cost");

            String productRecord = String.format(" %s, %s, %s, %f", id, productName, descriptions, cost);
            products.add(productRecord);

            moreProducts = SafeInput.getYNConfirm(scanner, "Do you want to add another product?");
        }

        String fileName = SafeInput.getNonZeroLenString(scanner, "Enter the file name to save the data");
        saveToFile(products, fileName);
    }

    private static void saveToFile(ArrayList<String> products, String fileName) {
        Path path = Path.of(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE)) {
            for (String product : products) {
                writer.write(product);
                writer.newLine();
            }
            System.out.println("Data saved to " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the data.");
            e.printStackTrace();
        }
    }
}
