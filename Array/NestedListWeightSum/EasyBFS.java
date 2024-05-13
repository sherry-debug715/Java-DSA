package Array.NestedListWeightSum;
// Lintcode prblem 551:https://www.lintcode.com/problem/551

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer,
 *     // rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds,
 *     // if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds,
 *     // if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */

public class EasyBFS {
    public int depthSum(List<NestedInteger> nestedList) {
        Queue<NestedInteger> queue = new LinkedList<>();
        for (NestedInteger n : nestedList) {
            queue.offer(n);
        }
        int output = 0;
        int level = 1;

        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                NestedInteger curNest = queue.poll();
                if (curNest.isInteger()) {
                    output += curNest.getInteger() * level;
                } else {
                    for (NestedInteger n : curNest.getList()) {
                        queue.offer(n);
                    }
                }
            }
            level += 1;
        }
        return output;
    }
}
