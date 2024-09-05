package Math;
// Leetcode 2028
// Time: 
public class FindMissingObservations {
    public int[] missingRolls(int[] rolls, int mean, int n) {
        int m = rolls.length;
        int sum = 0;
        for (int num : rolls) { // O(m)
            sum += num;
        }

        int missingInt = mean * (m + n) - sum;
        int carry = missingInt % n;
        if (missingInt / n > 6 || missingInt / n < 1 || (missingInt / n == 6 && carry > 0)) {
            return new int[0];
        }

        int[] res = new int[n];
        int num = missingInt / n;
        for (int i = 0; i < n; i++) { // O(n)
            int curNum = num;
            while (carry != 0 && curNum < 6) { // at most O(5) times
                curNum += 1;
                carry -= 1;
            }
            res[i] = curNum;
        }
        return res;
    }
}
