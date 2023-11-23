package TestCase;

// Interface for a printable object
interface Printable {
    void print();
}

// Base class representing a Person
class Person {
    String name;

    public Person(String name) {
        this.name = name;
    }

    public void introduce() {
        System.out.println("Hello, my name is " + name);
    }
}

// Student class inheriting from Person and implementing Printable interface
class Student extends Person implements Printable {
    int studentId;

    public Student(String name, int studentId) {
        super(name);
        this.studentId = studentId;
    }

    @Override
    public void print() {
        System.out.println("Printing student information: Name - " + name + ", Student ID - " + studentId);
    }
}

// Address class representing a street address
class Address {
    String street;
    String city;

    public Address(String street, String city) {
        this.street = street;
        this.city = city;
    }
}

// University class with a composition relationship with Address and aggregation relationship with Student
class University {
    String name;
    Address address;
    Student[] students;

    public University(String name, Address address, Student[] students) {
        this.name = name;
        this.address = address;
        this.students = students;
    }
}

// Department class with an association relationship with Student
class Department {
    String departmentName;
    Student[] students;

    public Department(String departmentName, Student[] students) {
        this.departmentName = departmentName;
        this.students = students;
    }
}

public class Test {
    public static void main(String[] args) {
        // Creating objects to demonstrate the relationships
        Student student1 = new Student("John Doe", 123);
        Student student2 = new Student("Jane Doe", 456);
        Student[] students = {student1, student2};

        Address universityAddress = new Address("123 University St", "Cityville");
        University university = new University("XYZ University", universityAddress, students);

        Department department = new Department("Computer Science", students);

        // Demonstrating the relationships
        student1.introduce();
        student1.print();

        System.out.println("University Name: " + university.name);
        System.out.println("University Address: " + university.address.street + ", " + university.address.city);

        System.out.println("Department Name: " + department.departmentName);
        System.out.println("Students in the Department: " + department.students.length);
    }
}
