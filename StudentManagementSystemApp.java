import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
    private String name;
    private int rollNumber;
    private int grade;
    private String details;

    public Student(String name, int rollNumber, int grade, String details) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.details = details;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade + ", Details: " + details;
    }
}

class StudentManagementSystem {
    private List<Student> students;

    public StudentManagementSystem() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                students.remove(student);
                System.out.println("Student with roll number " + rollNumber + " removed.");
                return;
            }
        }
        System.out.println("Student with roll number " + rollNumber + " not found.");
    }

    public void searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                System.out.println("Student found: " + student);
                return;
            }
        }
        System.out.println("Student with roll number " + rollNumber + " not found.");
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students in the system.");
        } else {
            System.out.println("List of all students:");
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    public void saveToFile(String filename) {
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(students);
            System.out.println("Student data saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile(String filename) {
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            students = (List<Student>) ois.readObject();
            System.out.println("Student data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

public class StudentManagementSystemApp {
    public static void main(String[] args) {
        StudentManagementSystem studentSystem = new StudentManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Add a new student");
            System.out.println("2. Remove a student");
            System.out.println("3. Search for a student");
            System.out.println("4. Display all students");
            System.out.println("5. Save students to file");
            System.out.println("6. Load students from file");
            System.out.println("7. Exit");

            System.out.print("Enter your choice (1-7): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter student's name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter student's roll number: ");
                    int rollNumber = scanner.nextInt();
                    System.out.print("Enter student's grade: ");
                    int grade = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter additional details (optional): ");
                    String details = scanner.nextLine();

                    Student student = new Student(name, rollNumber, grade, details);
                    studentSystem.addStudent(student);
                    System.out.println("Student added successfully.");
                    break;

                case 2:
                    System.out.print("Enter the roll number of the student to remove: ");
                    int rollToRemove = scanner.nextInt();
                    studentSystem.removeStudent(rollToRemove);
                    break;

                case 3:
                    System.out.print("Enter the roll number of the student to search: ");
                    int rollToSearch = scanner.nextInt();
                    studentSystem.searchStudent(rollToSearch);
                    break;

                case 4:
                    studentSystem.displayAllStudents();
                    break;

                case 5:
                    System.out.print("Enter the filename to save student data: ");
                    String saveFilename = scanner.next();
                    studentSystem.saveToFile(saveFilename);
                    break;

                case 6:
                    System.out.print("Enter the filename to load student data: ");
                    String loadFilename = scanner.next();
                    studentSystem.loadFromFile(loadFilename);
                    break;

                case 7:
                    System.out.println("Exiting the application.");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                    break;
            }
        }
    }
}

