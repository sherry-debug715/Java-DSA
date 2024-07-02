package UnionFind;
// lintcode 1014 

public class BricksFallingWhenHit {
    private int rowSize;
    private int colSize;
    private int[] POS = new int[] {-1, 0, 1, 0, -1};

    public int[] hitBricks(int[][] grid, int[][] hits) {
        rowSize = grid.length; colSize = grid[0].length;
        // STEP 1: make a copy of grid
        int[][] gridCopy = new int[rowSize][colSize];
        for (int r = 0; r < rowSize; r++) {
            for (int c = 0; c < colSize; c++) {
                gridCopy[r][c] = grid[r][c];
            }
        }

        // break all walls from hits 
        for (int[] hit : hits) {
            int x = hit[0], y = hit[1];
            gridCopy[x][y] = 0;
        }

        // STEP 2: construct union  
        int WALL = rowSize * colSize;
        UnionFind uf = new UnionFind(WALL + 1);
        // connect the first row to WALL 
        for (int c = 0; c < colSize; c++) {
            if (gridCopy[0][c] == 1) {
                uf.union(c, WALL); 
            }
        }
        // connect the rest of the walls to WALL if the UPPER, LEFT are also WALLs 
        for (int r = 1; r < rowSize; r++) {
            for (int c = 0; c < colSize; c++) {
                if (gridCopy[r][c] == 1) {
                    // upper 
                    if (gridCopy[r - 1][c] == 1) {
                        uf.union(getIndex(r - 1, c), getIndex(r, c));
                    }
                    // left 
                    if (c > 0 && gridCopy[r][c - 1] == 1) {
                        uf.union(getIndex(r, c - 1), getIndex(r, c));
                    }

                }
            }
        }

        // STEP 3: using the reversed order of hits to put each brick back one by one 
        int hitsLen = hits.length;
        int[] output = new int[hitsLen];
        for (int i = hitsLen - 1; i >= 0; i--) {
            int x = hits[i][0], y = hits[i][1];

            // if x, y cell is 0 in original grid, then no walls will fall 
            if (grid[x][y] == 0) {
                // output[i] defaults to 0
                continue;
            }

            // get the number of bricks attached to WALL before adding x, y to it 
            int originalCount = uf.getSize(WALL);
            // if x is on row 1, it should just be attached to WALL
            if (x == 0) {
                uf.union(y, WALL);
            }

            // check the neighbors for bricks 
            for (int j = 0; j < POS.length - 1; j++) {
                int newX = x + POS[j], newY = y + POS[j + 1];
                if (valid(newX, newY) && gridCopy[newX][newY] == 1) {
                    uf.union(getIndex(x, y), getIndex(newX, newY));
                }
            }
            // count how many bricks are connected to WALL now 
            int afterCount = uf.getSize(WALL);
            output[i] = Math.max(0, afterCount - originalCount - 1); // if afterCount == originalCount, it will result to -1.
            // put the brick back to gridCopy 
            gridCopy[x][y] = 1;
        }
        return output;
    }

    private boolean valid(int r, int c) {
        if (r < 0 || r >= rowSize) return false;
        if (c < 0 || c >= colSize) return false;
        return true;
    }

    private int getIndex(int r, int c) {
        return r * colSize + c;
    }


    class UnionFind {
        int[] fathers;
        int[] size;
        public UnionFind(int n) {
            fathers = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                fathers[i] = i;
                size[i] = 1;
            }
        }

        private int findCompress(int x) {
            int parent = fathers[x];
            while (parent != fathers[parent]) {
                parent = fathers[parent];
            }

            int temp = -1;
            int fa = fathers[x];
            while (fa != fathers[fa]) {
                temp = fathers[fa];
                fathers[fa] = parent;
                fa = temp;
            }
            return parent;
        }

        private void union(int x, int y) {
            int p1 = findCompress(x);
            int p2 = findCompress(y);
            if (p1 != p2) {
                fathers[p1] = p2;
                size[p2] += size[p1];
            }
        }

        private int getSize(int x) {
            int parent = findCompress(x);
            return size[parent];
        }
    }
}
