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

    public void setProductName(String productName) {
        this.productName = productName;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %f", id, productName, description, cost);

}
    public String toCSV() {
        return String.format("%s, %s, %s, %f", id, productName, description, cost);
    }
    public String toJSON() {
        return String.format("{\"id\": \"%s\", \"productName\": \"%s\", \"description\": \"%s\", \"cost\": %f}",
                id, productName, description, cost);
    }
    public String toXML() {
        return String.format("<Product id=\"%s\">\n" +
                "    <productName>%s</productName>\n" +
                "    <description>%s</description>\n" +
                "    <cost>%s</cost>\n" +
                "</Product>", id, productName, description, cost);
    }
}