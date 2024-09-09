
import org.junit.jupiter.api.Test;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class SafeInputTest {

    @Test
    public void testGetNonZeroLenString() {
        Scanner scanner = new Scanner("Hello\n");  // Add newline to simulate user hitting Enter
        String result = SafeInput.getNonZeroLenString(scanner, "Enter something");
        assertEquals("Hello", result, "Expected non-zero string 'Hello'");
    }

    @Test
    public void testGetInt() {
        Scanner scanner = new Scanner("5\n");  // Add newline to simulate user hitting Enter
        int result = SafeInput.getInt(scanner, "Enter a number");
        assertEquals(5, result, "Expected integer 5");
    }

    @Test
    public void testGetRangedInt() {
        Scanner scanner = new Scanner("10\n");  // Add newline to simulate user hitting Enter
        int result = SafeInput.getRangedInt(scanner, "Enter a number between 1 and 20", 1, 20);
        assertEquals(10, result, "Expected integer 10 within range");
    }

    @Test
    public void testGetYNConfirm() {
        Scanner scanner = new Scanner("Y\n");  // Add newline to simulate user hitting Enter
        boolean result = SafeInput.getYNConfirm(scanner, "Continue?");
        assertTrue(result, "Expected true for 'Y'");
    }
}
