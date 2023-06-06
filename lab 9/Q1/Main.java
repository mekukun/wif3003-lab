package Q1;

import java.util.ArrayList;
import java.util.List;

class Employee {
    private String name;
    private String gender;
    private int age;

    public Employee(String name, String gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Poseidon", "M", 23));
        employees.add(new Employee("Hera", "F", 18));
        employees.add(new Employee("Apollo", "M", 20));
        employees.add(new Employee("Athena", "F", 35));
        employees.add(new Employee("Demeter", "F", 50));

        employees.stream()
                .filter(employee -> employee.getGender().equals("F") && employee.getAge() >= 21)
                .forEach(employee -> System.out.println(employee.getName()));
    }
}
