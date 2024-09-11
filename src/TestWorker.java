
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestWorker {

    @Test
    public void testWorkerConstructor() {
        Worker worker = new Worker("001", "John", "Doe", "Mr.", 1980, 25.0);

        assertEquals("001", worker.getId());
        assertEquals("John", worker.getFirstName());
        assertEquals("Doe", worker.getLastName());
        assertEquals("Mr.", worker.getTitle());
        assertEquals(1980, worker.getYearOfBirth());
        assertEquals(25.0, worker.getHourlyRate());
    }

    @Test
    public void testCalculateWeeklyPay() {
        Worker worker = new Worker("002", "Jane", "Smith", "Ms.", 1990, 20.0);

        double pay = worker.calculateWeeklyPay(40);
        assertEquals(800.0, pay, "Weekly pay should be 800 for 40 hours");

        double payWithOvertime = worker.calculateWeeklyPay(50);
        assertEquals(1100.0, payWithOvertime, "Weekly pay should be 1100 for 50 hours");
    }

    @Test
    public void testDisplayWeeklyPay() {
        Worker worker = new Worker("003", "Bob", "Johnson", "Mr.", 1985, 30.0);

        String display40Hours = worker.displayWeeklyPay(40);
        String expected40Hours = "Regular Hours: 40.00, Regular Pay: $1200.00, Overtime Hours: 0.00, Overtime Pay: $0.00, Total Pay: $1200.00";
        assertEquals(expected40Hours, display40Hours, "Display should match for 40 hours");

        String display50Hours = worker.displayWeeklyPay(50);
        String expected50Hours = "Regular Hours: 40.00, Regular Pay: $1200.00, Overtime Hours: 10.00, Overtime Pay: $450.00, Total Pay: $1650.00";
        assertEquals(expected50Hours, display50Hours, "Display should match for 50 hours");
    }

    @Test
    public void testToCSV() {
        Worker worker = new Worker("004", "Alice", "Walker", "Ms.", 1975, 22.5);
        String expectedCSV = "004, Alice, Walker, Ms., 1975, 22.50";
        assertEquals(expectedCSV, worker.toCSV(), "CSV output should match");
    }

    @Test
    public void testToJSON() {
        Worker worker = new Worker("005", "Steve", "Martin", "Dr.", 1965, 35.0);
        String expectedJSON = "{\"id\": \"005\", \"firstName\": \"Steve\", \"lastName\": \"Martin\", \"title\": \"Dr.\", \"yearOfBirth\": 1965, \"hourlyRate\": 35.00}";
        assertEquals(expectedJSON, worker.toJSON(), "JSON output should match");
    }

    @Test
    public void testToXML() {
        Worker worker = new Worker("006", "Nancy", "White", "Prof.", 1980, 40.0);
        String expectedXML = "<Worker><ID>006</ID><FirstName>Nancy</FirstName><LastName>White</LastName><Title>Prof.</Title><YearOfBirth>1980</YearOfBirth><HourlyRate>40.00</HourlyRate></Worker>";
        assertEquals(expectedXML, worker.toXML(), "XML output should match");
    }
}
