import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    static int func(int n) {
        if (n <= 1) {
            return 0;
        }

        if (n % 2 == 0) {
            return func(n / 2) + 1;
        } else {
            return func(n - 1) - 1;
        }
    }

    public static void main(String[] args) {
        // Call func within the main method and print the result
        System.out.print(func(9));
    }
}


