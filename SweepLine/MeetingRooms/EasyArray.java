package SweepLine.MeetingRooms;

import java.util.Arrays;
import java.util.Comparator;

public class EasyArray {
        public boolean canAttendMeetings(int[][] intervals) {
        if (intervals.length == 0) {
            return true;
        }

        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));
        // System.out.println(intervals);
        for (int i = 1; i < intervals.length; i++) {
            int curStart = intervals[i][0];
            int prevEnd = intervals[i - 1][1];
            if (curStart < prevEnd) {
                return false;
            }
        }

        return true;
    }
}
