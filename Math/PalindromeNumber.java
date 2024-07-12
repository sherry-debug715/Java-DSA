package Math;
// leetcode 9
// time: O(digits length of x)
// Space: O(1)
public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        if (x < 10 && x >= 0) {
            return true;
        }
        if (x < 0 || x % 10 == 0) {
            return false;
        }
        
        int reverse = 0, copy = x;
        while (copy > 0) {
            int digit = copy % 10;
            reverse = reverse * 10 + digit;
            copy /= 10;
        }
        
        return reverse == x;
    }
}
