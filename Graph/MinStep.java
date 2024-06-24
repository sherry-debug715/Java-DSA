package Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

// lintcode 1832
// Time: O(n)
// Space: O(n)
public class MinStep {
    public int minimumStep(List<Integer> colors) {
        Map<Integer, Integer> minSteps = new HashMap<>(); // key: colors idx, value, min steps from 0 to key 
        Set<Integer> visitedGrid = new HashSet<>(); // visited idx 
        Set<Integer> visitedColor = new HashSet<>(); // visited colors[idx]; 
        int n = colors.size();

        List[] colorGrid = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            colorGrid[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < n; i++) {
            int c = colors.get(i);
            colorGrid[c].add(i);
        } 
     
        Queue<Integer> queue = new LinkedList<>();
        minSteps.put(0, 0);
        queue.offer(0);
        visitedGrid.add(0);

        while(!queue.isEmpty()) {
            int pos = queue.poll();
            int color = colors.get(pos);
            if (!visitedColor.contains(color)) {
                visitedColor.add(color);
                List<Integer> neighborIdx = colorGrid[color];
                for (int newPos : neighborIdx) {
                    if (!visitedGrid.contains(newPos)) {
                        minSteps.put(newPos, minSteps.get(pos) + 1);
                        queue.offer(newPos);
                        visitedGrid.add(newPos);
                    }
                }
            }

            // explore left and right 
            int leftPos = pos - 1;
            if (isValid(leftPos, n, visitedGrid)) {
                minSteps.put(leftPos, minSteps.get(pos) + 1);
                queue.offer(leftPos);
            }
            int rightPos = pos + 1;
            if (isValid(rightPos, n, visitedGrid)) {
                minSteps.put(rightPos, minSteps.get(pos) + 1);
                queue.offer(rightPos);
            }
        }
        return minSteps.get(n - 1);
    }

    private boolean isValid(int pos, int rowSize, Set<Integer> visitedGrid) {
        if (pos < 0 || pos >= rowSize) {
            return false;
        }
        if (visitedGrid.contains(pos)) {
            return false;
        }
        visitedGrid.add(pos);
        return true;
    } 
}
