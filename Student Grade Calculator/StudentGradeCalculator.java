import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeCalculator {

     static class Student {
        String name;
        ArrayList<Integer> marks;

        Student(String name, ArrayList<Integer> marks) {
            this.name = name.toLowerCase(); 
            this.marks = marks;
        }

        int getTotal() {
            int total = 0;
            for (int mark : marks) {
                total += mark;
            }
            return total;
        }

        double getAvgPercent() {
            return getTotal() / (double) marks.size();
        }

        String getGrade(double avgPercent) {
            if (avgPercent >= 90) {
                return "A";
            } else if (avgPercent >= 80) {
                return "B";
            } else if (avgPercent >= 70) {
                return "C";
            } else if (avgPercent >= 60) {
                return "D";
            } else {
                return "F";
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        while (true) {
            System.out.println("\n1. Add student and their marks");
            System.out.println("2. Calculate and display student's result");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter student's name: ");
                    String name = sc.next();
                    ArrayList<Integer> marks = new ArrayList<>();
                    System.out.print("Enter the number of subjects: ");
                    int numSubjects = sc.nextInt();
                    for (int i = 0; i < numSubjects; i++) {
                        System.out.print("Enter marks for subject " + (i + 1) + ": ");
                        marks.add(sc.nextInt());
                    }
                    students.add(new Student(name, marks));
                    break;
                case 2:
                    if (students.isEmpty()) {
                        System.out.println("No students found. Please add a student first.");
                        break;
                    }
                    System.out.print("Enter the student's name to calculate their result: ");
                    String studentName = sc.next().toLowerCase(); 
                    Student selectedStudent = null;
                    for (Student student : students) {
                        if (student.name.equals(studentName)) {
                            selectedStudent = student;
                            break;
                        }
                    }
                    if (selectedStudent == null) {
                        System.out.println("Student not found.");
                        break;
                    }
                    int totalMarks = selectedStudent.getTotal();
                    double avgPercent = selectedStudent.getAvgPercent();
                    String grade = selectedStudent.getGrade(avgPercent);
                    System.out.println("\nResults for " + selectedStudent.name + ":");
                    System.out.println("Total Marks: " + totalMarks);
                    System.out.println("Average Percentage: " + avgPercent + "%");
                    System.out.println("Grade: " + grade);
                    break;
                case 3:
                    System.out.println("Exiting........");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }
    }
}
