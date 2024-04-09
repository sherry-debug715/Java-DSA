public class Main {

    public static void main(String[] args) {

        // Function
        int score = 100;
        char grade = getGrade(score); // char grade = 'A';

        int number1 = 10;
        int number2 = 20;
        swap(number1, number2);

        System.out.println(number1);
        System.out.println(number2);

        // String
        System.out.println("Hello world");
        String str = "a string";
        String str2 = str + "hello";

        System.out.println(str2);

        String str3 = "a";
        String str4 = "I am " + 19 + " years old"; // 19 -> "19", "I am 19 years old"

        System.out.println(str.length());

        for (int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            System.out.print(c);
            System.out.print(' ');
        }
        System.out.println();

        String strNumber = "123";
        System.out.println(Integer.valueOf(strNumber));
        System.out.println(Integer.parseInt(strNumber));
    }

    public static void swap(int a, int b) {
        int t = a;
        a = b;
        b = t;
    }

    public static void simpleVoidFunction() {
        System.out.println("Simple void function");
    }

    public static void print(int age) {
        System.out.println("My age is: " + age);
    }

    public static int sum(int a, int b) {
        return a + b;
    }

    public static char getGrade(int score) {

        if (score >= 85) {
            return 'A';
        } else if (score >= 75) {
            return 'B';
        } else if (score >= 60) {
            return 'C';
        } else {
            return 'D';
        }
    }

}
