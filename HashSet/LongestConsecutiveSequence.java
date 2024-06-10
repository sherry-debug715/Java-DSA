package HashSet;
// Lintcode 124 
import java.util.HashSet;
import java.util.Set;
// Time: O(N)
// Space: O(N);
public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] num) {
        // set for constant look up time 
        Set<Integer> numSet = new HashSet<>();
        for (int n : num) {
            numSet.add(n);
        }

        int maxLen = 1;
        for (int n : num) {
            if (numSet.contains(n - 1) || !numSet.contains(n + 1)) {
                continue;
            }
            int curN = n;
            int count = 0;
            while (numSet.contains(curN)) {
                curN += 1;
                count += 1;
            }
            maxLen = Math.max(maxLen, count);
        }
        return maxLen;
    }
}
