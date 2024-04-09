import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class EncapsulationExample {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input the number of students:");
        int n = scanner.nextInt();

        Student[] students = new Student[n];
        for (int i = 0; i < n; ++i) {
            System.out.println("Please input the name and score of student " + (i + 1) + ", separated by space:");
            students[i] = new Student(scanner.next(), scanner.nextInt());
        }

        System.out.println("\nInput: ");
        for (Student student : students) {
            student.speak();
        }

        Comparator<Student> comparator = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o2.score - o1.score;
            }
        };
        Arrays.sort(students, comparator);

        System.out.println("\nSorted: ");
        for (Student student : students) {
            student.speak();
        }
    }
}
