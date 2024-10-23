package Greedy;

import SweepLine.MergeIntervals.Interval;

// Lintcode 1242
// Time: O(nlogn) + O(n)
// Space: O(1)
public class NonOverlappingIntervals {
    public int eraseOverlapIntervals(List<Interval> intervals) {
        // sort intervals by the start num 
        Collections.sort(intervals, (a, b) -> {
            if (a.start == b.start) {
                return a.end - b.end;
            }
            return a.start - b.start;
        });

        int currEnd = Integer.MIN_VALUE;
        int res = 0;
        for (Interval i : intervals) {
            if (i.start >= currEnd) {
                currEnd = i.end;
            } else {
                // currEnd should be as small as possible to allow max non-overlapping intervals 
                currEnd = Math.min(currEnd, i.end);
                res += 1;
            }
        }
        return res;
    }
}
