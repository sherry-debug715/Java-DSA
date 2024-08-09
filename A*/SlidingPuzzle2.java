package A*;
// lintcode 794
public class SlidingPuzzle2 {
    class State {
        int steps;
        int estimate;
        public State(int _steps, int _estimate) {
            steps = _steps;
            estimate = _estimate;
        }
    }

    public int minMoveStep(int[][] initState, int[][] finalState) {
        // convert both states to string 
        // O(18);
        String source = matrixToString(initState); 
        String target = matrixToString(finalState);
        // use a check function to determine if it's possible to transform initialState of 8-puzzle board to 
        // the final state using sliding tiles. 
        // O(1)
        if (!check(source, target)) {
            return -1;
        }

        Map<String, State> open = new HashMap<>();
        Set<String> close = new HashSet<>(); // visited set 

        open.put(source, new State(0, getH(source, target)));
        while (open.size() > 0) {
            String curPuzzle = getMin(open); // O(4)
            if (curPuzzle.equals(target)) {
                return open.get(curPuzzle).steps;
            }
            close.add(curPuzzle);

            // explore other moves 
            for (String next : getPuzzles(curPuzzle)) { // O(4)
                if (!close.contains(next)) {
                    if (!open.containsKey(next) || open.get(next).steps > open.get(curPuzzle).steps + 1) {
                        int nextSteps = open.get(curPuzzle).steps + 1;
                        int nextEstimate = getH(next, target);
                        open.put(next, new State(nextSteps, nextSteps + nextEstimate));
                    }
                }
            }

            open.remove(curPuzzle);
        }
        return -1;
    }

    private List<String> getPuzzles(String puzzle) {
        int[] POS = new int[] {-1, 0, 1, 0, -1};
        List<String> valid = new ArrayList<>();
        int zeroIdx = puzzle.indexOf('0');
        int r = zeroIdx / 3, c = zeroIdx % 3;

        for (int i = 0; i < POS.length - 1; i++) {
            int nr = r + POS[i], nc = c + POS[i + 1];
            if (nr >= 0 && nr < 3 && nc >= 0 && nc < 3) {
                char[] puzzleArr = puzzle.toCharArray();
                int newIdx = nr * 3 + nc;
                puzzleArr[r * 3 + c] = puzzleArr[newIdx];
                puzzleArr[newIdx] = '0';
                valid.add(new String(puzzleArr));
            }
        }
        return valid;
    }
    // getMin returns the puzzle string with the min estimate future distance 
    private String getMin(Map<String, State> open) {
        int min = Integer.MAX_VALUE;
        String puzzle = "";
        for (String key : open.keySet()) {
            if (open.get(key).estimate < min) {
                min = open.get(key).estimate;
                puzzle = key;
            }
        }
        return puzzle;
    }

    private int getH(String state, String target) {
        int totalH = 0;
        for (int i = 0; i < 9; i++) {
            int idx = state.indexOf(target.charAt(i));
            int tr = i / 3, tc = i % 3;
            int sr = idx / 3, sc = idx % 3;
            totalH += Math.abs(sr - tr) + Math.abs(sc - tc);
        }
        return totalH;
    }

    private String matrixToString(int[][] grid) {
        StringBuilder str = new StringBuilder();
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                str.append(grid[r][c]);
            }
        }
        return str.toString();
    }

    private boolean check(String source, String target) {
        char[] s = source.toCharArray();
        char[] t = target.toCharArray();
        int r1 = 0, r2 = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < i; j++) {
                if (s[j] != '0' && s[i] != '0' && s[j] > s[i]) {
                    r1 += 1;
                }
                if (t[i] != '0' && t[j] != '0' && t[j] > t[i]) {
                    r2 += 1;
                }
            }
        }

        return r1 % 2 == r2 % 2;
    }
}
