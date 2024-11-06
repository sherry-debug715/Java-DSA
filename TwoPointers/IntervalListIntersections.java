package TwoPointers;

import java.util.List;

// Leetcode 986
public class IntervalListIntersections {
        List<int[]> outputList = new ArrayList<>();
        int i = 0, j = 0;
        
        while (i < firstList.length && j < secondList.length) {
            int start1 = firstList[i][0], start2 = secondList[j][0];
            int end1 = firstList[i][1], end2 = secondList[j][1];
            int start = Math.max(start1, start2);
            int end = Math.min(end1, end2);
            if (start <= end) {
                outputList.add(new int[]{start, end});
            }
            if (end1 <= end2) {
                i += 1;
            } else {
                j += 1;
            }
        }
        
        return outputList.toArray(new int[outputList.size()][]);
    }
}
