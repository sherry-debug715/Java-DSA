package SweepLine;

import java.util.List;

// Lintcode 1397
public class DigitalCoverage {
        /**
     * @param intervals: The intervals
     * @return: The answer
     */
     // Time: O(n + m) where n is the size of intervals, m is the value of maxEnd
     // Space: O(100005) so O(1) 
     public int digitalCoverage(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) {
            return -1;
        }
        // the left and right endpoints of the interval are greater than 0 not more than 10^5.
        int[] overLap = new int[100005];
        int maxEnd = 1;
        // add start and end of each interval to overLap 
        for (Interval i : intervals) {
            int start = i.start, end = i.end;
            maxEnd = Math.max(maxEnd, end + 1);
            overLap[start] += 1;
            overLap[end + 1] -= 1;
        }

        // count the overlap hour in overLap while updating the most coverage time 
        int res = 1;
        int mostCoverage = overLap[1];
        for (int i = 2; i <= maxEnd; i++) {
            overLap[i] += overLap[i - 1];
            if (overLap[i] > mostCoverage) {
                mostCoverage = overLap[i];
                res = i;
            }
        }
        return res;
    }
}
