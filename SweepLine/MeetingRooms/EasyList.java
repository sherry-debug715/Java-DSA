package SweepLine.MeetingRooms;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Interval {
     int start, end;
     Interval(int start, int end) {
         this.start = start;
         this.end = end;
     }
 }

public class EasyList {
    public boolean canAttendMeetings(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) {
            return true;
        }

        Collections.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval int1, Interval int2) {
                return int1.start - int2.start;
            }
        });

        for (int i = 1; i < intervals.size(); i++) {
            if (intervals.get(i).start < intervals.get(i - 1).end) {
                return false;
            }
        }

        return true;
    }
}
