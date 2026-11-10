import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** Serializes several employee records and reads them back from a file. */
public class EmployeeSerialization {
    private static final String DATA_FILE = "employees.dat";

    public static void saveEmployees(String fileName, List<Employee> employees) {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(fileName))) {
            output.writeObject(employees);
            System.out.println("Saved " + employees.size() + " employee records to " + fileName);
        } catch (IOException e) {
            System.err.println("Could not serialize employees: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Employee> loadEmployees(String fileName) {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<Employee>) input.readObject();
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            System.err.println("Could not deserialize employees: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee(201, "Nina Kapoor", "Engineering", 72000.00),
                new Employee(202, "Arjun Rao", "Human Resources", 58000.00),
                new Employee(203, "Sara Khan", "Finance", 64000.00));

        saveEmployees(DATA_FILE, employees);
        System.out.println("Employee records read from file:");
        for (Employee employee : loadEmployees(DATA_FILE)) {
            System.out.println(employee);
        }
    }
}

class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int employeeId;
    private final String name;
    private final String department;
    private final double salary;

    Employee(int employeeId, String name, String department, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{id=" + employeeId + ", name='" + name + "', department='"
                + department + "', salary=" + String.format("%.2f", salary) + "}";
    }
}
