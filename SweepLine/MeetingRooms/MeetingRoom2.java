package SweepLine.MeetingRooms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//Definition of Interval:
class Interval {
    int start, end;
    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class Point {
    int time;
    int flag;

    Point(int _time, int _flag) {
        time = _time;
        flag = _flag;
    }
    // PointComparator is a static field (class variable) not a static method, therefore need to end with a ";".
    public static Comparator<Point> PointComparator = new Comparator<Point>() {
        public int compare(Point p1, Point p2) {
            if (p1.time == p2.time) return p1.flag - p2.flag;
            else return p1.time - p2.time;
        }
    };
}
 
public class MeetingRoom2 {
    public int minMeetingRooms(List<Interval> intervals) {
        List<Point> time = new ArrayList<>(intervals.size() * 2);
        for (Interval i : intervals) {
            time.add(new Point(i.start, 1));  // Add point for start of interval
            time.add(new Point(i.end, -1));   // Add point for end of interval
        }

        // Sort the points based on the custom comparator
        Collections.sort(time, Point.PointComparator);

        int counter = 0;
        int ongoingMeeting = 0;
        for (Point p : time) {  // Ensure this uses Point, not Pointer
            ongoingMeeting += p.flag;
            counter = Math.max(counter, ongoingMeeting);
        }

        return counter;
    }
}
