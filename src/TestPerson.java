import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestPerson {

    @Test
    public void testPersonConstructor() {
        Person person = new Person("123", "John", "Doe", "Mr.", 1985);

        assertEquals("123", person.getId(), "ID should be 123");
        assertEquals("John", person.getFirstName(), "First name should be John");
        assertEquals("Doe", person.getLastName(), "Last name should be Doe");
        assertEquals("Mr.", person.getTitle(), "Title should be Mr.");
        assertEquals(1985, person.getYearOfBirth(), "Year of birth should be 1985");
    }

    @Test
    public void testGetters() {
        Person person = new Person("456", "Jane", "Smith", "Ms.", 1990);

        assertEquals("456", person.getId());
        assertEquals("Jane", person.getFirstName());
        assertEquals("Smith", person.getLastName());
        assertEquals("Ms.", person.getTitle());
        assertEquals(1990, person.getYearOfBirth());
    }
    @Test
    public void testSetters() {
        Person person = new Person("456", "Jane", "Smith", "Ms.", 1990);

        person.setFirstName("Janet");
        person.setLastName("Johnson");
        person.setTitle("Dr.");
        person.setYearOfBirth(1975);

        assertEquals("Janet", person.getFirstName());
        assertEquals("Johnson", person.getLastName());
        assertEquals("Dr.", person.getTitle());
        assertEquals(1975, person.getYearOfBirth());
    }

    @Test
    public void testYearOfBirthValidation() {
        Person person = new Person("789", "Alice", "Johnson", "Dr.", 1975);

        person.setYearOfBirth(1930);
        assertEquals(1975, person.getYearOfBirth());

        person.setYearOfBirth(1950);
        assertEquals(1950, person.getYearOfBirth());
    }

    @Test
    public void testToString() {

        Person person = new Person("789", "Alice", "Johnson", "Dr.", 1975);

        String expectedString = "789, Alice, Johnson, Dr., 1975";
        assertEquals(expectedString, person.toString(), "toString output should match");
    }
}
