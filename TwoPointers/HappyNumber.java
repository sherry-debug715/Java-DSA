package TwoPointers;
// Lintcode problem 488:  https://www.lintcode.com/problem/488/
// Time: O(N)
public class HappyNumber {
    public boolean isHappy(int n) {
        int slow = n;
        int fast = counter(n);
        while (slow != fast) {
            slow = counter(slow);
            fast = counter(fast);
            fast = counter(fast);
        }

        return slow == fast && slow == 1;
    }

    private int counter(int n) {
        int nextNum = 0;
        while (n > 0) {
            int lastDigit = n % 10;
            nextNum += (lastDigit * lastDigit);
            n /= 10;
        }
        return nextNum;
    }
}
