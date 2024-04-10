package String;

public class DecreaseToBePanlindrome {
    public int numberOfOperations(String s) {
        if (s == null || s == "") return 0;

        int left = 0;
        int right = s.length() - 1;
        int minStep = 0;

        while (left < right) {
            minStep += Math.abs(s.charAt(left) - s.charAt(right));
            left ++;
            right --;
        }

        return minStep;
    }
}
