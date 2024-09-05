package Math;
// leetcode 1945
// Time: O(n + k)
// Space: O(1)
public class SumOfDigitsOfStringAfterConvert {
    public int getLucky(String s, int k) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) { // O(n * 2)
            int temp = s.charAt(i) - 'a' + 1;
            while (temp >= 1) {
                sum += temp % 10;
                temp /= 10;
            }
        }
        k -= 1;    
        for (int i = 0; i < k; i++) { // O(k - 1)
            int digitSum = 0;
            while (sum > 0) { // O(log(sum))
                digitSum += sum % 10;
                sum /= 10;
            }
            sum = digitSum; // reassign num to the digit sum for the next iteration
        }
        
        return sum;
    }
}
