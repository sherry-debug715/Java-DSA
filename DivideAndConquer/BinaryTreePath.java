package DivideAndConquer;
// Lintcode 480: https://www.lintcode.com/problem/480/?fromId=161&_from=collection
public class BinaryTreePath {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> output = new ArrayList<>();
        dfs(root, output, new ArrayList<String>());
        return output;
    }

    private void dfs(TreeNode root, List<String> output, List<String> curPath) {
        if (root == null) {
            return;
        }

        curPath.add(Integer.toString(root.val));
        dfs(root.left, output, curPath);
        dfs(root.right, output, curPath);
        if (root.left == null && root.right == null) {
            output.add(String.join("->",curPath));
        }
        curPath.remove(curPath.size() - 1);
    }
}
