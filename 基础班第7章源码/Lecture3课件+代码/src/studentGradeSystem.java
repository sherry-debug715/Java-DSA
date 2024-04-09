import java.util.Scanner;

public class studentGradeSystem {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input the number of students:");
        int n = scanner.nextInt();

        String[] names = new String[n];
        int[] scores = new int[n];

        for (int i = 0; i < n; ++i) {
            System.out.println("Please input the name and score of student " + (i + 1) + ", separated by space:");
            names[i] = scanner.next();
            scores[i] = scanner.nextInt();
        }

        // names: ["Tom", "Jack", "Zhang"]
        // scores: [43, 65, 100]

        System.out.println("\nInput: ");
        for (int i = 0; i < n; ++i) {
            System.out.println(names[i] + ": " + scores[i]);
        }

        for (int i = 0; i < n; ++i) {
            int maxIndex = i;
            for (int j = i + 1; j < n; ++j) {
                if (scores[j] > scores[maxIndex]) {
                    maxIndex = j;
                }
            }

            int t = scores[i];
            scores[i] = scores[maxIndex];
            scores[maxIndex] = t;

            String str = names[i];
            names[i] = names[maxIndex];
            names[maxIndex] = str;
        }

        System.out.println("\nSorted: ");
        for (int i = 0; i < n; ++i) {
            System.out.println(names[i] + ": " + scores[i]);
        }
    }
}
