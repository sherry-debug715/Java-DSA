package DivideAndConquer;

import java.util.List;

class MultiTreeNode {
     int val;
     List<MultiTreeNode> children;
        MultiTreeNode(int x) { val = x; }
 }
    
class ResultType {
    int maxPath;
    int maxUp;
    int maxDown;
    public ResultType(int _maxPath, int _maxUp, int _maxDown) {
        maxPath = _maxPath;
        maxUp = _maxUp;
        maxDown = _maxDown;
    }
}
public class BTLongestConsecutiveSequence3 {
    public int longestConsecutive3(MultiTreeNode root) {
        ResultType result = exploreTree(root);
        return result.maxPath;
    }

    private ResultType exploreTree(MultiTreeNode root) {
        if (root == null) {
            return new ResultType(0, 0, 0);
        }

        int maxPath = 0, maxUp = 0, maxDown = 0;
        for (MultiTreeNode node : root.children) {
            ResultType childResult = exploreTree(node);
            // increase 
            if (node.val - 1 == root.val) {
                maxUp = Math.max(maxUp, childResult.maxUp + 1);
            }
            // decrease 
            if (node.val + 1 == root.val) {
                maxDown = Math.max(maxDown, childResult.maxDown + 1);
            }
            // update maxPath 
            maxPath = Math.max(maxPath, childResult.maxPath);
        }
        
        maxPath = Math.max(maxPath, maxUp + maxDown + 1);
        return new ResultType(maxPath, maxUp, maxDown);

    }
}
