package SweepLine.MergeIntervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class InputArrays {
       public int[][] merge(int[][] intervals) {
        List<int[]> merged = new ArrayList<>();
        // sort intervals by start time
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));
        merged.add(intervals[0]);

        for (int[] interval : intervals) {
            int[] prevInterval = merged.get(merged.size() - 1);
            // if not overlap:
            if (interval[0] > prevInterval[1]) {
                merged.add(interval);
            } else {
                prevInterval[0] = Math.min(prevInterval[0], interval[0]);
                prevInterval[1] = Math.max(prevInterval[1], interval[1]);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    } 
}
