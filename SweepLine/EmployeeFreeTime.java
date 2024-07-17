package SweepLine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// lintcode 850 
// Time: O(nlogn)
// Space: O(n)
public class EmployeeFreeTime {
    class Point {
        int val;
        int flag;
        public Point(int _val, int _flag) {
            val = _val;
            flag = _flag;
        }
    }

    public List<Interval> employeeFreeTime(int[][] schedule) {
        List<Interval> output = new ArrayList<>();
        if (schedule == null || schedule.length == 0 || schedule[0].length == 0) {
            return output;
        }

        List<Point> es = new ArrayList<>(); // es: all employee schedules 
        for (int[] s : schedule) {
            for (int i = 0; i < s.length; i += 2) {
                es.add(new Point(s[i], -1));
                es.add(new Point(s[i + 1], 1));
            }
        }
        Collections.sort(es, (a, b) -> {
            if (a.val == b.val) {
                return a.flag - b.flag; // start goes first 
            } else {
                return a.val - b.val;
            }
        }); 

        int matched = 0, left = Integer.MIN_VALUE, right = Integer.MAX_VALUE; 
        for (Point p : es) {
            matched += p.flag;
            if (matched == -1 && left != Integer.MIN_VALUE) {
                output.add(new Interval(left, p.val));
                left = Integer.MIN_VALUE;
                right = Integer.MAX_VALUE; 
                
            }
            if (matched == 0) {
                left = p.val;
            }
        }
        return output;
    }
}
