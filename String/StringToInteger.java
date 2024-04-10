package String;
// Lintcode problem 241: https://www.lintcode.com/problem/241/

public class StringToInteger {
    public int stringToInteger(String target) {
        int num = 0;
        int startPos = 0;

        if (target.charAt(0) == '-') {
            startPos = 1;
        }

        for (int i = startPos; i < target.length(); i++) {
            num = num * 10 + (target.charAt(i) - '0');
        }

        if (startPos == 1) num = -num;
        return num;
    }
}
