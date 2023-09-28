import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

record Student(String name, int rollNumber, String grade) {

    @Contract(pure = true)
    @Override
    public @NotNull String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }
}

class StudentManagementSystem {
    private final List<Student> students;

    public StudentManagementSystem() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public boolean removeStudent(int rollNumber) {
        for (Student student : students) {
            if (student.rollNumber() == rollNumber) {
                students.remove(student);
                return true;
            }
        }
        return false;
    }

    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.rollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    public void displayAllStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public List<Student> getAllStudents() {
        return students;
    }
}

public class studentsystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagementSystem sms = new StudentManagementSystem();


        loadStudentsFromFile(sms);

        while (true) {
            System.out.println("\nStudent Management System Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addStudent(scanner, sms);
                case 2 -> removeStudent(scanner, sms);
                case 3 -> searchStudent(scanner, sms);
                case 4 -> sms.displayAllStudents();
                case 5 -> {
                    saveStudentsToFile(sms);
                    System.out.println("Exiting Student Management System.");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void addStudent(Scanner scanner, StudentManagementSystem sms) {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        System.out.print("Enter roll number: ");
        int rollNumber;
        try {
            rollNumber = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
        } catch (NumberFormatException e) {
            System.out.println("Invalid roll number format.");
            return;
        }

        System.out.print("Enter grade: ");
        String grade = scanner.nextLine().trim();

        if (grade.isEmpty()) {
            System.out.println("Grade cannot be empty.");
            return;
        }

        Student student = new Student(name, rollNumber, grade);
        sms.addStudent(student);
        System.out.println("Student added successfully.");
    }

    private static void removeStudent(Scanner scanner, StudentManagementSystem sms) {
        System.out.print("Enter roll number of the student to remove: ");
        int rollNumber;
        try {
            rollNumber = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
        } catch (NumberFormatException e) {
            System.out.println("Invalid roll number format.");
            return;
        }

        boolean removed = sms.removeStudent(rollNumber);
        if (removed) {
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student with roll number " + rollNumber + " not found.");
        }
    }

    private static void searchStudent(Scanner scanner, StudentManagementSystem sms) {
        System.out.print("Enter roll number of the student to search: ");
        int rollNumber;
        try {
            rollNumber = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
        } catch (NumberFormatException e) {
            System.out.println("Invalid roll number format.");
            return;
        }

        Student student = sms.searchStudent(rollNumber);
        if (student != null) {
            System.out.println("Student found:");
            System.out.println(student);
        } else {
            System.out.println("Student with roll number " + rollNumber + " not found.");
        }
    }

    private static void loadStudentsFromFile(StudentManagementSystem sms) {
        try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0].trim();
                    int rollNumber = Integer.parseInt(parts[1].trim());
                    String grade = parts[2].trim();
                    Student student = new Student(name, rollNumber, grade);
                    sms.addStudent(student);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading student data from file.");
        }
    }

    private static void saveStudentsToFile(StudentManagementSystem sms) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt"))) {
            for (Student student : sms.getAllStudents()) {
                writer.write(student.name() + ", " + student.rollNumber() + ", " + student.grade());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving student data to file.");
        }
    }
}
