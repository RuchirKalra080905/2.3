import java.util.*;
import java.util.stream.*;
import java.util.function.*;
import java.util.Comparator;
import java.util.Map;

class Employee {
    String name;
    int age;
    double salary;
    Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
    public String toString() {
        return name + " - " + age + " - " + salary;
    }
}

class Student {
    String name;
    double marks;
    Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }
    public String toString() {
        return name + " - " + marks;
    }
}

class Product {
    String name;
    double price;
    String category;
    Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
    public String toString() {
        return name + " (" + category + ") - " + price;
    }
}

public class LambdaStreamDemo {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("Anshul", 24, 50000),
            new Employee("Rahul", 30, 75000),
            new Employee("Priya", 22, 60000),
            new Employee("Neha", 28, 55000)
        );
        System.out.println("Sort by Name:");
        employees.stream().sorted((e1, e2) -> e1.name.compareTo(e2.name)).forEach(System.out::println);
        System.out.println("\nSort by Age:");
        employees.stream().sorted(Comparator.comparingInt(e -> e.age)).forEach(System.out::println);
        System.out.println("\nSort by Salary (Descending):");
        employees.stream().sorted((e1, e2) -> Double.compare(e2.salary, e1.salary)).forEach(System.out::println);

        List<Student> students = Arrays.asList(
            new Student("Aman", 82),
            new Student("Riya", 68),
            new Student("Vikas", 91),
            new Student("Tina", 76),
            new Student("Deepak", 59)
        );
        System.out.println("\nStudents Scoring Above 75% Sorted by Marks:");
        students.stream()
                .filter(s -> s.marks > 75)
                .sorted(Comparator.comparingDouble(s -> s.marks))
                .map(s -> s.name)
                .forEach(System.out::println);

        List<Product> products = Arrays.asList(
            new Product("Laptop", 85000, "Electronics"),
            new Product("Phone", 55000, "Electronics"),
            new Product("Shirt", 1200, "Clothing"),
            new Product("Jeans", 2500, "Clothing"),
            new Product("Refrigerator", 60000, "Appliances"),
            new Product("Microwave", 18000, "Appliances")
        );
        System.out.println("\nProducts Grouped by Category:");
        Map<String, List<Product>> grouped = products.stream().collect(Collectors.groupingBy(p -> p.category));
        grouped.forEach((k, v) -> {
            System.out.println(k + ": " + v);
        });

        System.out.println("\nMost Expensive Product in Each Category:");
        Map<String, Optional<Product>> maxPrice = products.stream()
                .collect(Collectors.groupingBy(p -> p.category, Collectors.maxBy(Comparator.comparingDouble(p -> p.price))));
        maxPrice.forEach((k, v) -> System.out.println(k + ": " + v.get()));

        double avgPrice = products.stream().collect(Collectors.averagingDouble(p -> p.price));
        System.out.println("\nAverage Price of All Products: " + avgPrice);
    }
}