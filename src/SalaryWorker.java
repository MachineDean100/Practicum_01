public class SalaryWorker extends Worker {
    private double annualSalary;

    public SalaryWorker(String id, String firstName, String lastName, String title, int yearOfBirth, double annualSalary) {
        super(id, firstName, lastName, title, yearOfBirth, annualSalary / 52 / 40);
        this.annualSalary = annualSalary;
    }

    @Override
    public double calculateWeeklyPay(double hoursWorked) {
        return annualSalary / 52;
    }

    @Override
    public String displayWeeklyPay(double hoursWorked) {
        double weeklyPay = calculateWeeklyPay(hoursWorked);
        return String.format("Weekly salary pay: $%.2f", weeklyPay);
    }

    @Override
    public String toCSV() {
        return String.format("%s, %.2f", super.toCSV(), annualSalary);
    }

    @Override
    public String toJSON() {
        return String.format("{\"id\": \"%s\", \"firstName\": \"%s\", \"lastName\": \"%s\", \"title\": \"%s\", \"yearOfBirth\": %d, \"hourlyRate\": %.2f, \"annualSalary\": %.2f}",
                getId(), getFirstName(), getLastName(), getTitle(), getYearOfBirth(), getHourlyRate(), annualSalary);
    }

    @Override
    public String toXML() {
        return String.format("<SalaryWorker>%s<AnnualSalary>%.2f</AnnualSalary></SalaryWorker>", super.toXML(), annualSalary);
    }
}

