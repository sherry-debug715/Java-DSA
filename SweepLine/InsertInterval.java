package SweepLine;

import java.util.ArrayList;
import java.util.List;
// Time: O(N)
// Space: O(N)
public class InsertInterval {
     public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> output = new ArrayList<>();

        for (int i = 0; i < intervals.length; i++) {
            int[] curInterval = intervals[i];
            // if new Interval need to be inserted at position 0 or merging into new interval
            // is completed and position is located.
            if (newInterval[1] < curInterval[0]) {
                output.add(newInterval);
                for (int j = i; j < intervals.length; j++) {
                    output.add(intervals[j]);
                }
                return output.toArray(new int[output.size()][]);
            } else if (curInterval[1] < newInterval[0]) { // if they don't overlap
                output.add(curInterval);
            } else {
                newInterval[0] = Math.min(curInterval[0], newInterval[0]);
                newInterval[1] = Math.max(curInterval[1], newInterval[1]);
            }
        }

        // if last interval from intervals array is merged into new interval and the function 
        // didn't return on line 14
        output.add(newInterval);
        return output.toArray(new int[output.size()][]);
    }
}
