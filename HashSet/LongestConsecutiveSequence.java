package HashSet;
// Lintcode 124 
import java.util.HashSet;
import java.util.Set;
// Time: O(N)
// Space: O(N);
public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] num) {
        if (num == null || num.length == 0) {
            return 0;
        }
        int maxCount = 0;
        Set<Integer> storage = new HashSet<>();
        for (int n : num) {
            storage.add(n);
        }

        for (int n : num) {
            // only calcute in increasing order, to avoid repetitive counting. 
            if (storage.contains(n - 1)) {
                continue;
            }
            int curCount = 1;
            int curNum = n;
            while (storage.contains(curNum + 1)) {
                curCount += 1;
                curNum += 1;
            }
            maxCount = Math.max(maxCount, curCount);
        }
        return maxCount;
    }
}
