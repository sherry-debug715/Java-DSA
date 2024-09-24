package DFS;
// Lintcode 1855 
public class ReachDestination {
    /**
     * @param sx: the start x
     * @param sy: the start y
     * @param dx: the destination x
     * @param dy: the destination y
     * @return: whether you can reach the destination
     */
    // Time: O(log(Math.max(dx, dy)))
    // Space: O(1)
    public boolean reachDestinationSolution1(int sx, int sy, int dx, int dy) {
        while (dx > sx && dy > sy) {
            if (dx > dy) {
                dx %= dy;
            } else if (dy > dx) {
                dy %= dx;
            }
        }
        // break out of while loop at the last second step 
        return (sx == dx && (dy - sy) % sx == 0) || (sy == dy && (dx - sx) % sy == 0);
    }

     public boolean reachDestinationSolution2(int sx, int sy, int dx, int dy) {
        while (dx >= sx && dy >= sy) {
            if (dx == sx && dy == sy) {
                return true;
            }
            if (dx > dy) {
                dx = dx - dy;
            } else if (dy > dx) {
                dy = dy - dx;
            } else {
                break;
            }
        }

        return false;
    }
}
