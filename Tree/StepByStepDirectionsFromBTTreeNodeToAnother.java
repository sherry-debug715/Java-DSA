package Tree;
// Leetcode 2096 
// Time: O(N)
// Space: O(N)
public class StepByStepDirectionsFromBTTreeNodeToAnother {
    public String getDirections(TreeNode root, int startValue, int destValue) {
        // step one, dfs to find the paths to both startNode and destValue 
        StringBuilder pathToStart = new StringBuilder();
        StringBuilder pathToDest = new StringBuilder();
        findPath(root, startValue, pathToStart);
        findPath(root, destValue, pathToDest);
        // step two, find the LCA of both nodes by removing the common prefix of both path
        int i = 0; 
        while (i < pathToStart.length() && i < pathToDest.length() && pathToStart.charAt(i) == pathToDest.charAt(i)) {
            i += 1;
        }
        StringBuilder output = new StringBuilder();
        for (int j = i; j < pathToStart.length(); j++) {
            output.append("U");
        }
        output.append(pathToDest.substring(i));
        return output.toString();
    }

    private boolean findPath(TreeNode root, int targetValue, StringBuilder path) {
        if (root == null) {
            return false;
        }
        if (root.val == targetValue) {
            return true;
        }
        path.append("L");
        if (findPath(root.left, targetValue, path)) {
            return true;
        }
        path.deleteCharAt(path.length() - 1);
        path.append("R");
        if (findPath(root.right, targetValue, path)) {
            return true;
        }
        path.deleteCharAt(path.length() - 1);
        return false;
    }
}
