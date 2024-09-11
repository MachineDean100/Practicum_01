import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestSalaryWorker {

    @Test
    public void testSalaryWorkerConstructor() {
        SalaryWorker salaryWorker = new SalaryWorker("007", "James", "Bond", "Agent", 1980, 100000);

        assertEquals("007", salaryWorker.getId());
        assertEquals("James", salaryWorker.getFirstName());
        assertEquals("Bond", salaryWorker.getLastName());
        assertEquals("Agent", salaryWorker.getTitle());
        assertEquals(1980, salaryWorker.getYearOfBirth());
        assertEquals(100000, salaryWorker.calculateWeeklyPay(0) * 52, 0.01); // Checking if annual salary is correct
    }

    @Test
    public void testCalculateWeeklyPay() {
        SalaryWorker salaryWorker = new SalaryWorker("008", "Alicia", "Keys", "Singer", 1981, 52000);
        assertEquals(1000, salaryWorker.calculateWeeklyPay(40));
    }

    @Test
    public void testDisplayWeeklyPay() {
        SalaryWorker salaryWorker = new SalaryWorker("009", "Oprah", "Winfrey", "Host", 1954, 104000);
        String expected = "Weekly salary pay: $2000.00";
        assertEquals(expected, salaryWorker.displayWeeklyPay(40));
    }

    @Test
    public void testToCSV() {
        SalaryWorker salaryWorker = new SalaryWorker("010", "Will", "Smith", "Actor", 1968, 500000);
        String expected = "010, Will, Smith, Actor, 1968, 240.38, 500000.00"; // 240.38 is the calculated hourly rate based on salary
        assertEquals(expected, salaryWorker.toCSV());
    }

    @Test
    public void testToJSON() {
        SalaryWorker salaryWorker = new SalaryWorker("011", "Elon", "Musk", "CEO", 1971, 750000);
        String expected = "{\"id\": \"011\", \"firstName\": \"Elon\", \"lastName\": \"Musk\", \"title\": \"CEO\", \"yearOfBirth\": 1971, \"hourlyRate\": 360.58, \"annualSalary\": 750000.00}";
        assertEquals(expected, salaryWorker.toJSON());
    }

    @Test
    public void testToXML() {
        SalaryWorker salaryWorker = new SalaryWorker("012", "Steve", "Jobs", "Founder", 1955, 1250000);
        String expected = "<SalaryWorker><Worker><ID>012</ID><FirstName>Steve</FirstName><LastName>Jobs</LastName><Title>Founder</Title><YearOfBirth>1955</YearOfBirth><HourlyRate>600.96</HourlyRate></Worker><AnnualSalary>1250000.00</AnnualSalary></SalaryWorker>";
        assertEquals(expected, salaryWorker.toXML());
    }
}

