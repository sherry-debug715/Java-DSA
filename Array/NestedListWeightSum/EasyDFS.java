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

public class EasyDFS {
    int output = 0;
    public int depthSum(List<NestedInteger> nestedList) {
        dfs(nestedList, 1);
        return output;
    }

    private void dfs(List<NestedInteger> nestedList, int level) {
        for (NestedInteger n : nestedList) {
            if (n.isInteger()) {
                output += n.getInteger() * level;
            } else {
                dfs(n.getList(), level + 1);
            }
        }
    }
}
