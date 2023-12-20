import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", rollNumber=" + rollNumber +
                ", grade='" + grade + '\'' +
                '}';
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
        students.removeIf(student -> student.getRollNumber() == rollNumber);
    }

    public Student searchStudent(int rollNumber) {
        return students.stream()
                .filter(student -> student.getRollNumber() == rollNumber)
                .findFirst()
                .orElse(null);
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }
}

public class StudentManagementApp {
    private static Scanner scanner = new Scanner(System.in);
    private static StudentManagementSystem studentManagementSystem = new StudentManagementSystem();

    public static void main(String[] args) {
        int choice;
        do {
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    removeStudent();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    displayAllStudents();
                    break;
                case 5:
                    System.out.println("Exiting the application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        } while (choice != 5);
    }

    private static void displayMenu() {
        System.out.println("\nStudent Management System Menu");
        System.out.println("1. Add a new student");
        System.out.println("2. Remove a student");
        System.out.println("3. Search for a student");
        System.out.println("4. Display all students");
        System.out.println("5. Exit");
    }

    private static void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        int rollNumber;
        do {
            System.out.print("Enter student roll number: ");
            rollNumber = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (rollNumber <= 0) {
                System.out.println("Invalid roll number. Please enter a number greater than 0.");
            }
        } while (rollNumber <= 0);

        int grade;
        do {
            System.out.print("Enter student grade (1-10): ");
            grade = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (grade < 1 || grade > 10) {
                System.out.println("Invalid grade. Please enter a number between 1 and 10.");
            }
        } while (grade < 1 || grade > 10);

        Student newStudent = new Student(name, rollNumber, String.valueOf(grade));
        studentManagementSystem.addStudent(newStudent);
        System.out.println("Student added successfully!");
    }

    private static void removeStudent() {
        int grade;
        do {
            System.out.print("Enter student grade to remove (1-10): ");
            grade = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (grade < 1 || grade > 10) {
                System.out.println("Invalid grade. Please enter a number between 1 and 10.");
            }
        } while (grade < 1 || grade > 10);

        System.out.print("Enter the roll number of the student to remove: ");
        int rollNumber = scanner.nextInt();

        if (studentManagementSystem.searchStudent(rollNumber) != null) {
            studentManagementSystem.removeStudent(rollNumber);
            System.out.println("Student removed successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }

    private static void searchStudent() {
        int grade;
        do {
            System.out.print("Enter student grade to search (1-10): ");
            grade = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (grade < 1 || grade > 10) {
                System.out.println("Invalid grade. Please enter a number between 1 and 10.");
            }
        } while (grade < 1 || grade > 10);

        int rollNumber;
        do {
            System.out.print("Enter the roll number of the student to search: ");
            rollNumber = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (rollNumber <= 0) {
                System.out.println("Invalid roll number. Please enter a number greater than 0.");
            }
        } while (rollNumber <= 0);

        Student foundStudent = studentManagementSystem.searchStudent(rollNumber);
        if (foundStudent != null && foundStudent.getGrade().equals(String.valueOf(grade))) {
            System.out.println("Student found:\n" + foundStudent);
        } else {
            System.out.println("Student not found in grade " + grade + "!");
        }
    }
    private static void displayAllStudents() {
        List<Student> allStudents = studentManagementSystem.getAllStudents();

        if (allStudents.isEmpty()) {
            System.out.println("No students in the system.");
        } else {
            System.out.println("All students in the system:");
            for (Student student : allStudents) {
                System.out.println(student);
            }
        }
    }
}
