package Array;
// Leetcode 957: https://leetcode.com/problems/prison-cells-after-n-days/
public class PrisonCellsAfterNDays {
    public int[] prisonAfterNDays(int[] cells, int n) {
        Map<String, Integer> seenMap = new HashMap<>();
        boolean hasCycle = false;
        int cycleLen = 0;
        for (int i = 0; i < n; i++) {
            int[] next = getNext(cells);
            String key = Arrays.toString(next);
            if (seenMap.containsKey(key)) {
                cycleLen = i - seenMap.get(key);
                hasCycle = true;
                break;
            } else {
                seenMap.put(key, i);
                cells = next;
            }
        }
        
        // shrink down the iteration 
        if (hasCycle) {
            for (int i = 0; i < n % cycleLen; i++) {
                int[] next = getNext(cells);
                cells = next;
            }
        }
        return cells;
    }
    
    private int[] getNext(int[] cells) {
        int[] next = new int[cells.length];
        for (int i = 1; i < cells.length - 1; i++) {
            next[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
        }
        return next;
    }
}
