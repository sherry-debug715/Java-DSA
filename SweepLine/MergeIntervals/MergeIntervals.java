package SweepLine.MergeIntervals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public class Interval {
        int start, end;
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    
// Lintcode problem 156: https://www.lintcode.com/problem/156/?showListFe=true&page=1&problemTypeId=2&tagIds=389&ordering=level&pageSize=50
// Solution one: Greedy
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

// Solution 2, Interval 
class Solution {
    /**
     * @param intervals: interval list.
     * @return: A new interval list.
     */
    class Point {
        int val;
        int flag;
        public Point(int _val, int _flag) {
            val = _val;
            flag = _flag;
        }
    }

    public List<Interval> merge(List<Interval> intervals) {
        List<Point> points = new ArrayList<>();
        for (Interval i : intervals) {
            points.add(new Point(i.start, -1));
            points.add(new Point(i.end, 1));
        }

        // sort points by val, if val equal, start first end second 
        Collections.sort(points, (a, b) -> {
            if (a.val == b.val) {
                return a.flag - b.flag;
            } else {
                return a.val - b.val;
            }
        });

        List<Interval> output = new ArrayList<>();
        // find the intial start 
        int start = 0, end = 0, matched = 0;
        for (Point p : points) {
            if (matched == 0) {
                start = p.val;
            }
            matched += p.flag;
            if (matched == 0) {
                end = p.val;
                output.add(new Interval(start, end));
            }
        }
        return output;
    }
} 
