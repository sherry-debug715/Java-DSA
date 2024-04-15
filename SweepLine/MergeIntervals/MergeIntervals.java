package SweepLine.MergeIntervals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
// Lintcode problem 156: https://www.lintcode.com/problem/156/?showListFe=true&page=1&problemTypeId=2&tagIds=389&ordering=level&pageSize=50
public class MergeIntervals {
    class Interval {
            int start, end;
            Interval(int start, int end) {
                this.start = start;
                this.end = end;
            }
        }

    public List<Interval> merge(List<Interval> intervals) {
        if (intervals.isEmpty()) {
            return intervals;
        }

        // sort intervals by start value 
        intervals.sort(Comparator.comparing(interval -> interval.start));
        List<Interval> results = new ArrayList<Interval>();

        Interval lastInterval = null;
        for (Interval interval: intervals) {
            if (lastInterval == null || interval.start > lastInterval.end) {
                results.add(interval);
                lastInterval = interval;
            } else {
                lastInterval.end = Math.max(lastInterval.end, interval.end);
            }
        }
        return results;
    }
    
}
