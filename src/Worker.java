public class Worker extends Person {
    private double hourlyRate;

    public Worker(String id, String firstName, String lastName, String title, int yearOfBirth, double hourlyRate) {
        super(id, firstName, lastName, title, yearOfBirth);
        this.hourlyRate = hourlyRate;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public double calculateWeeklyPay(double hoursWorked) {
        if (hoursWorked <= 40) {
            return hoursWorked * hourlyRate;
        } else {
            double overtime = hoursWorked - 40;
            return (40 * hourlyRate) + (overtime * (hourlyRate * 1.5));
        }
    }

    public String displayWeeklyPay(double hoursWorked) {
        double regularPay = (hoursWorked <= 40) ? hoursWorked * hourlyRate : 40 * hourlyRate;
        double overtimeHours = (hoursWorked > 40) ? hoursWorked - 40 : 0;
        double overtimePay = overtimeHours * (hourlyRate * 1.5);
        double totalPay = regularPay + overtimePay;

        return String.format("Regular Hours: %.2f, Regular Pay: $%.2f, Overtime Hours: %.2f, Overtime Pay: $%.2f, Total Pay: $%.2f",
                (hoursWorked <= 40) ? hoursWorked : 40, regularPay, overtimeHours, overtimePay, totalPay);

    }

    @Override
    public String toCSV() {
        return String.format("%s, %.2f", super.toCSV(), hourlyRate);
    }

    @Override
    public String toJSON() {
        return String.format("{\"id\": \"%s\", \"firstName\": \"%s\", \"lastName\": \"%s\", \"title\": \"%s\", \"yearOfBirth\": %d, \"hourlyRate\": %.2f}",
                getId(), getFirstName(), getLastName(), getTitle(), getYearOfBirth(), hourlyRate);
    }
    @Override
    public String toXML() {
        return String.format("<Worker><ID>%s</ID><FirstName>%s</FirstName><LastName>%s</LastName><Title>%s</Title><YearOfBirth>%d</YearOfBirth><HourlyRate>%.2f</HourlyRate></Worker>",
                getId(), getFirstName(), getLastName(), getTitle(), getYearOfBirth(), hourlyRate);
    }

    @Override
    public String toString() {
        return String.format("%s, Hourly Rate: %.2f", super.toString(), hourlyRate);
    }
}
