package Tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// leetcode 199
// Time: O(N)
// Space: O(N)
public class BinaryTreeRightSideView {
        public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()){
            int n = q.size();
            
            
            for(int i = 0; i< n; i++){
                TreeNode curNode = q.poll();
                if(i == n - 1){
                    res.add(curNode.val);
                }
                if(curNode.left!=null){
                    q.offer(curNode.left);
                }
                if(curNode.right!=null){
                    q.offer(curNode.right);
                }
            }
        }
        return res;
    }
}
