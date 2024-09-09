import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestProduct {

    @Test
    public void testProductConstructor() {
        Product product = new Product("123", "Soap", "Cleans", 10.000000);

        assertEquals("123", product.getId(), "ID should be 123");
        assertEquals("Soap", product.getProductName(), "Product name should be Soap");
        assertEquals("Cleans", product.getDescription(), "Description should be Cleans");
        assertEquals(10.000000, product.getCost(), "Cost should be 10.");
    }

    @Test
    public void testGetters() {
        Product product = new Product("456", "Umbrella", "Protects", 11.000000);

        assertEquals("456", product.getId());
        assertEquals("Umbrella", product.getProductName());
        assertEquals("Protects", product.getDescription());
        assertEquals(11.000000, product.getCost());

}
    @Test
    public void testSetters() {
        Product product = new Product("456", "Umbrella", "Protects", 11.000000);


        product.setProductName("Raincoat");
        product.setDescription("Covers");
        product.setCost(12.000000);

        assertEquals("Raincoat", product.getProductName());
        assertEquals("Covers", product.getDescription());
        assertEquals(12.000000, product.getCost());
    }


    @Test
    public void testToString() {

        Product product = new Product("452", "Umbrella", "Protects", 11.000000);

        String expectedString = "452, Umbrella, Protects, 11.000000";
        assertEquals(expectedString, product.toString(), "toString output should match");
    }
}