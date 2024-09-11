import java.util.ArrayList;

public class InheritanceDemo {
    public static void main(String[] args) {
        ArrayList<Worker> workers = new ArrayList<>();

        workers.add(new Worker("001", "John", "Doe", "Mr.", 1980, 20.00));
        workers.add(new Worker("002", "Jane", "Doe", "Ms.", 1985, 22.50));
        workers.add(new Worker("003", "Jack", "Doe", "Mr.", 1990, 18.00));

        workers.add(new SalaryWorker("004", "Alice", "Smith", "Mrs.", 1975, 52000));
        workers.add(new SalaryWorker("005", "Bob", "Johnson", "Mr.", 1980, 65000));
        workers.add(new SalaryWorker("006", "Charlie", "Brown", "Mr.", 1995, 72000));

        double[] hoursWorked = {40, 50, 40};

        for (int week = 0; week < hoursWorked.length; week++) {
            System.out.printf("Week %d Pay:\n", week + 1);
            for (Worker worker : workers) {
                System.out.printf("%s: %s\n", worker.getFirstName(), worker.displayWeeklyPay(hoursWorked[week]));
            }
            System.out.println();
        }
    }
}
