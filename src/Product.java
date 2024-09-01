public class Product {
    private String id;
    private String productName;
    private String description;
    private double cost;

    public Product(String id, String productName, String description, Double cost) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.cost = cost;
    }
public String getId() {
        return id;
    }
    public String getProductName() {
        return productName;
    }
    public String getDescription() {
        return description;
    }
    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %f", id, productName, description, cost);
    }
}