package Graph;

import java.util.HashSet;
import java.util.Set;

// Leetcode 874
// Time: O(Math.max(commands.length, obstacles.length))
// Space: O(obstacles.length)
public class WalkingRobotSimulation {
    public int robotSim(int[] commands, int[][] obstacles) {
        Set<String> obs = new HashSet<>();
        for (int[] o : obstacles) {
            obs.add(o[0] + "," + o[1]);
        }

        int[][] POS =  {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int posIdx = 0, x = 0, y = 0;
        int res = 0;

        for (int c : commands) {
            if (c == -1) {
                posIdx = (posIdx + 1) % 4;
            } else if (c == -2) {
                posIdx = (posIdx + 3) % 4;
            } else {
                int newX = x, newY = y;
                for (int i = 0; i < c; i++) { // move k times, one step at a time
                    newX += POS[posIdx][0];
                    newY += POS[posIdx][1];
                    if (obs.contains(newX + "," + newY)) {
                        break;
                    }
                    x = newX;
                    y = newY;
                    res = Math.max(res, x*x + y*y);
                }
            }
        }
        return res;
    }
}
