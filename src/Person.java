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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setYearOfBirth(int yearOfBirth) {
        if (yearOfBirth >= 1940 && yearOfBirth <= 2040) {
            this.yearOfBirth = yearOfBirth;
        } else {
            System.out.println("Invalid year of birth. Must be between 1940 and 2010.");
        }
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %d", id, firstName, lastName, title, yearOfBirth);
    }

    public String toCSV() {
        return String.format("%s, %s, %s, %s, %d", id, firstName, lastName, title, yearOfBirth);
    }

    public String toJSON() {
        return String.format("{\"id\": \"%s\", \"firstName\": \"%s\", \"lastName\": \"%s\", \"title\": \"%s\", \"yearOfBirth\": %d}",
                id, firstName, lastName, title, yearOfBirth);
    }

    public String toXML() {
        return String.format("<Person><ID>%s</ID><FirstName>%s</FirstName><LastName>%s</LastName><Title>%s</Title><YearOfBirth>%d</YearOfBirth></Person>",
                id, firstName, lastName, title, yearOfBirth);
    }

}