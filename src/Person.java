public class Person {
    private String id;
    private String firstName;
    private String lastName;
    private String title;
    private int yearOfBirth;

    public Person(String id, String firstName, String lastName, String title, int yearOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.yearOfBirth = yearOfBirth;
    }
    public String getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getTitle() {
        return title;
    }
    public int getYearOfBirth() {
        return yearOfBirth;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %d", id, firstName, lastName, title, yearOfBirth);
    }
}