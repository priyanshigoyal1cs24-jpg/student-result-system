import java.util.ArrayList;
import java.util.Scanner;

public class StudentResultManagementSystem {
    private static ArrayList<Student> students = new ArrayList<>();
    private static ArrayList<Result> results = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            displayMainMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    studentMenu();
                    break;
                case 2:
                    resultMenu();
                    break;
                case 3:
                    viewAllStudentsWithResults();
                    break;
                case 4:
                    System.out.println("Thank you for using Student Result Management System!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 4);
    }

    private static void displayMainMenu() {
        System.out.println("\n========== Student Result Management System ==========");
        System.out.println("1. Student Management");
        System.out.println("2. Result Management");
        System.out.println("3. View All Students with Results");
        System.out.println("4. Exit");
        System.out.println("======================================================");
    }

    private static void studentMenu() {
        int choice;
        do {
            System.out.println("\n========== Student Management ==========");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Back to Main Menu");
            System.out.println("=========================================");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 5);
    }

    private static void addStudent() {
        System.out.println("\n--- Add New Student ---");
        System.out.print("Enter Student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();

        if (findStudentById(studentId) != null) {
            System.out.println("Student with ID " + studentId + " already exists!");
            return;
        }

        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Course: ");
        String course = scanner.nextLine();

        Student student = new Student(studentId, name, course);
        students.add(student);
        System.out.println("Student added successfully!");
    }

    private static void viewAllStudents() {
        System.out.println("\n--- All Students ---");
        if (students.isEmpty()) {
            System.out.println("No students found!");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    private static void updateStudent() {
        System.out.println("\n--- Update Student ---");
        System.out.print("Enter Student ID to update: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();

        Student student = findStudentById(studentId);
        if (student == null) {
            System.out.println("Student with ID " + studentId + " not found!");
            return;
        }

        System.out.println("Current details: " + student);
        System.out.print("Enter new Name (press Enter to keep current): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            student.setName(name);
        }

        System.out.print("Enter new Course (press Enter to keep current): ");
        String course = scanner.nextLine();
        if (!course.isEmpty()) {
            student.setCourse(course);
        }

        System.out.println("Student updated successfully!");
        System.out.println("Updated details: " + student);
    }

    private static void deleteStudent() {
        System.out.println("\n--- Delete Student ---");
        System.out.print("Enter Student ID to delete: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();

        Student student = findStudentById(studentId);
        if (student == null) {
            System.out.println("Student with ID " + studentId + " not found!");
            return;
        }

        results.removeIf(result -> result.getStudentId() == studentId);
        students.remove(student);
        System.out.println("Student and associated results deleted successfully!");
    }

    private static Student findStudentById(int studentId) {
        for (Student student : students) {
            if (student.getStudentId() == studentId) {
                return student;
            }
        }
        return null;
    }

    private static void resultMenu() {
        int choice;
        do {
            System.out.println("\n========== Result Management ==========");
            System.out.println("1. Add Result");
            System.out.println("2. View All Results");
            System.out.println("3. Update Result");
            System.out.println("4. Delete Result");
            System.out.println("5. Back to Main Menu");
            System.out.println("========================================");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addResult();
                    break;
                case 2:
                    viewAllResults();
                    break;
                case 3:
                    updateResult();
                    break;
                case 4:
                    deleteResult();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 5);
    }

    private static void addResult() {
        System.out.println("\n--- Add New Result ---");
        System.out.print("Enter Student ID: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();

        if (findStudentById(studentId) == null) {
            System.out.println("Student with ID " + studentId + " does not exist! Please add student first.");
            return;
        }

        if (findResultByStudentId(studentId) != null) {
            System.out.println("Result for Student ID " + studentId + " already exists!");
            return;
        }

        System.out.print("Enter Marks: ");
        int marks = scanner.nextInt();
        scanner.nextLine();

        if (marks < 0 || marks > 100) {
            System.out.println("Invalid marks! Marks should be between 0 and 100.");
            return;
        }

        String grade = calculateGrade(marks);
        Result result = new Result(studentId, marks, grade);
        results.add(result);
        System.out.println("Result added successfully!");
        System.out.println("Grade assigned: " + grade);
    }

    private static void viewAllResults() {
        System.out.println("\n--- All Results ---");
        if (results.isEmpty()) {
            System.out.println("No results found!");
        } else {
            for (Result result : results) {
                System.out.println(result);
            }
        }
    }

    private static void updateResult() {
        System.out.println("\n--- Update Result ---");
        System.out.print("Enter Student ID to update result: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();

        Result result = findResultByStudentId(studentId);
        if (result == null) {
            System.out.println("Result for Student ID " + studentId + " not found!");
            return;
        }

        System.out.println("Current details: " + result);
        System.out.print("Enter new Marks: ");
        int marks = scanner.nextInt();
        scanner.nextLine();

        if (marks < 0 || marks > 100) {
            System.out.println("Invalid marks! Marks should be between 0 and 100.");
            return;
        }

        result.setMarks(marks);
        result.setGrade(calculateGrade(marks));
        System.out.println("Result updated successfully!");
        System.out.println("Updated details: " + result);
    }

    private static void deleteResult() {
        System.out.println("\n--- Delete Result ---");
        System.out.print("Enter Student ID to delete result: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();

        Result result = findResultByStudentId(studentId);
        if (result == null) {
            System.out.println("Result for Student ID " + studentId + " not found!");
            return;
        }

        results.remove(result);
        System.out.println("Result deleted successfully!");
    }

    private static Result findResultByStudentId(int studentId) {
        for (Result result : results) {
            if (result.getStudentId() == studentId) {
                return result;
            }
        }
        return null;
    }

    private static String calculateGrade(int marks) {
        if (marks >= 90) {
            return "A+";
        } else if (marks >= 80) {
            return "A";
        } else if (marks >= 70) {
            return "B";
        } else if (marks >= 60) {
            return "C";
        } else if (marks >= 50) {
            return "D";
        } else {
            return "F";
        }
    }

    private static void viewAllStudentsWithResults() {
        System.out.println("\n--- All Students with Results ---");
        if (students.isEmpty()) {
            System.out.println("No students found!");
            return;
        }

        for (Student student : students) {
            System.out.println("\n" + student);
            Result result = findResultByStudentId(student.getStudentId());
            if (result != null) {
                System.out.println("  Result: " + result);
            } else {
                System.out.println("  Result: Not available");
            }
        }
    }
}

