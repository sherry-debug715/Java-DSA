package SweepLine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Definition of Interval:
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

    public Point(int _time, int _flag) {
        time = _time;
        flag = _flag;
    }

    // static field 
    public static Comparator<Point> ComparePoint = new Comparator<Point>() {
        public int compare(Point p1, Point p2) {
            if (p1.time == p2.time) return p1.flag - p2.flag;
            else return p1.time - p2.time;
        }
    };
}

public class NumOfAirplanesInSky {
       /**
     * @param airplanes: An interval array
     * @return: Count of airplanes are in the sky.
     */
    public int countOfAirplanes(List<Interval> airplanes) {
        List<Point> time = new ArrayList<Point>();

        for (Interval point : airplanes) {
            time.add(new Point(point.start, 1));
            time.add(new Point(point.end, -1));
        }

        // sort time by rules defined by static field Point.ComparePoint 
        Collections.sort(time, Point.ComparePoint);

        // counting airplanes flying at the time 
        int flying = 0;
        // tracking max airplanes ever in air
        int counter = 0; 

        for (Point p : time) {
            flying += p.flag;
            counter = Math.max(counter, flying);
        } 

        return counter;
    } 
}
