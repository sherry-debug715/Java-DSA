package UnionFind;
// Lintcode 434
// Time: on average O(1), worst case, every cell has to be updated leading to O(m * n)
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NumOfIslands2 {
    int[][] POS = {
        {-1, 0},
        {1, 0},
        {0, -1},
        {0, 1}
    };

    Map<Integer, Integer> fathers = new HashMap<>();
    int islandSize = 0;

    private int findCompress(int x) {
        int parent = fathers.get(x);
        while (parent != fathers.get(parent)) {
            parent = fathers.get(parent);
        }

        int fa = fathers.get(x);
        int temp = -1;
        while (fa != fathers.get(fa)) {
            temp = fathers.get(fa);
            fathers.put(fa, parent);
            fa = temp;
        }
        return parent;
    }

    private void union(int x, int y) {
        int p1 = findCompress(x);
        int p2 = findCompress(y);
        if (p1 == p2) {
            return;
        }
        fathers.put(p1, p2);
        islandSize -= 1;
    }

    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        List<Integer> output = new ArrayList<>();
        Set<Integer> islands = new HashSet<>();

        if (operators == null || operators.length == 0) {
            return output;
        }
        for (Point pos : operators) {
            int r = pos.x, c = pos.y;
            int key = r * m + c;
            if (islands.contains(key)) {
                output.add(islandSize);
                continue;
            }
            fathers.put(key, key);
            islands.add(key);
            islandSize += 1;

            for (int[] neighbor : POS) {
                int oldR = r + neighbor[0], oldC = c + neighbor[1], oldKey = oldR * m + oldC;
                if (inValid(oldR, oldC, n, m, islands)) {
                    continue;
                }
                union(key, oldKey);
            }
            output.add(islandSize);
        }

        return output;
    }

    private boolean inValid(int r, int c, int rowSize, int colSize, Set<Integer> islands) {
        if (r < 0 || r >= rowSize) return true;
        if (c < 0 || c >= colSize) return true;
        if (!islands.contains(r * colSize + c)) return true;
        return false;
    }
}
